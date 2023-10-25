package com.example.tonwalletapp.di.data.ton

import com.example.tonwalletapp.data.remote.TonClient
import com.example.tonwalletapp.data.remote.TonClientImpl
import com.example.tonwalletapp.data.remote.state.TonStateModule
import com.example.tonwalletapp.data.remote.state.TonStateModuleImpl
import com.example.tonwalletapp.data.remote.ton_lite_client.TonLiteClientFactory
import com.example.tonwalletapp.data.remote.ton_lite_client.TonLiteClientFactoryImpl
import com.example.tonwalletapp.data.remote.transfer.TonTransferModule
import com.example.tonwalletapp.data.remote.transfer.TonTransferModuleImpl
import com.example.tonwalletapp.data.remote.wallet.TonWalletModule
import com.example.tonwalletapp.data.remote.wallet.TonWalletModuleImpl
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
    fun provideTonLiteClientFactory(httpClient: HttpClient):TonLiteClientFactory{
        return TonLiteClientFactoryImpl(httpClient)
    }

    @Singleton
    @Provides
    fun provideTonWalletModule(
        tonLiteClientFactory: TonLiteClientFactory,
        tonStateModule: TonStateModule
    ):TonWalletModule{
        return TonWalletModuleImpl(
            tonLiteClientFactory = tonLiteClientFactory,
            tonStateModule = tonStateModule
        )
    }

    @Singleton
    @Provides
    fun provideTonStateModule(
        liteClientFactory: TonLiteClientFactory
    ):TonStateModule{
        return TonStateModuleImpl(
            liteClientFactory = liteClientFactory
        )
    }

    @Singleton
    @Provides
    fun provideTonTransferModule(
        tonWalletModule: TonWalletModule,
        tonStateModule: TonStateModule,
        liteClientFactory: TonLiteClientFactory
    ):TonTransferModule{
        return TonTransferModuleImpl(
            tonStateModule = tonStateModule,
            tonWalletModule = tonWalletModule,
            liteClientFactory = liteClientFactory
        )
    }

    @Singleton
    @Provides
    fun provideTonСlient(
        tonWalletModule: TonWalletModule,
        tonStateModule: TonStateModule,
        tonTransferModule: TonTransferModule
    ):TonClient{
        return TonClientImpl(
            tonWalletModule = tonWalletModule,
            tonStateModule = tonStateModule,
            tonTransferModule = tonTransferModule
        )
    }

}