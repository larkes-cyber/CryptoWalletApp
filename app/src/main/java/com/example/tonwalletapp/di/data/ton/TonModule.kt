package com.example.tonwalletapp.di.data.ton

import com.example.tonwalletapp.data.ton_remote.ton_client.TonClient
import com.example.tonwalletapp.data.ton_remote.ton_client.TonClientImpl
import com.example.tonwalletapp.data.ton_remote.module.state.TonStateModule
import com.example.tonwalletapp.data.ton_remote.module.state.TonStateModuleImpl
import com.example.tonwalletapp.data.ton_remote.ton_config.TonLiteClientConfigFactory
import com.example.tonwalletapp.data.ton_remote.ton_config.TonLiteClientConfigFactoryImpl
import com.example.tonwalletapp.data.ton_remote.module.transfer.TonTransferModule
import com.example.tonwalletapp.data.ton_remote.module.transfer.TonTransferModuleImpl
import com.example.tonwalletapp.data.ton_remote.module.wallet.TonWalletModule
import com.example.tonwalletapp.data.ton_remote.module.wallet.TonWalletModuleImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TonModule {

    @Singleton
    @Provides
    fun provideTonLiteClientFactory(httpClient: HttpClient): TonLiteClientConfigFactory {
        return TonLiteClientConfigFactoryImpl(httpClient)
    }

    @Singleton
    @Provides
    fun provideTonWalletModule(
        tonLiteClientConfigFactory: TonLiteClientConfigFactory,
        tonStateModule: TonStateModule
    ): TonWalletModule {
        return TonWalletModuleImpl(
            tonLiteClientConfigFactory = tonLiteClientConfigFactory,
            tonStateModule = tonStateModule
        )
    }

    @Singleton
    @Provides
    fun provideTonStateModule(
        liteClientFactory: TonLiteClientConfigFactory
    ): TonStateModule {
        return TonStateModuleImpl(
            liteClientFactory = liteClientFactory
        )
    }

    @Singleton
    @Provides
    fun provideTonTransferModule(
        tonWalletModule: TonWalletModule,
        tonStateModule: TonStateModule,
        liteClientFactory: TonLiteClientConfigFactory
    ): TonTransferModule {
        return TonTransferModuleImpl(
            tonStateModule = tonStateModule,
            tonWalletModule = tonWalletModule,
            liteClientFactory = liteClientFactory
        )
    }

    @Singleton
    @Provides
    fun provideTonClient(
        tonWalletModule: TonWalletModule,
        tonStateModule: TonStateModule,
        tonTransferModule: TonTransferModule
    ): TonClient {
        return TonClientImpl(
            tonWalletModule = tonWalletModule,
            tonStateModule = tonStateModule,
            tonTransferModule = tonTransferModule
        )
    }

}