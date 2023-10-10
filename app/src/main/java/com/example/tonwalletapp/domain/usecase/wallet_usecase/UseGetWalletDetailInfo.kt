package com.example.tonwalletapp.domain.usecase.wallet_usecase

import android.util.Log
import com.example.tonwalletapp.domain.model.WalletDetail
import com.example.tonwalletapp.domain.repository.WalletRepository
import com.example.tonwalletapp.until.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseGetWalletDetailInfo(
    private val walletRepository: WalletRepository
) {

    operator fun invoke(id:String):Flow<Resource<WalletDetail>> = flow {

        emit(Resource.Loading())

        try {
            val detail = walletRepository.getDetailWalletInfo(id)
            emit(Resource.Success(detail))
        }catch (e:Exception){
            Log.d("sfsdfsdfsdf", e.toString())
            emit(Resource.Error(e.message!!))
        }

    }

}