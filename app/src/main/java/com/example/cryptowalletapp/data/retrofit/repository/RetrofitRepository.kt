package com.example.cryptowalletapp.data.retrofit.repository

import android.util.Log
import com.example.cryptowalletapp.common.Constants.CRYPTO_LOGO_URL
import com.example.cryptowalletapp.data.retrofit.api.CompareApi
import com.example.cryptowalletapp.data.retrofit.api.PaprikaApi
import com.example.cryptowalletapp.data.retrofit.model.CoinDetail
import com.example.cryptowalletapp.data.retrofit.model.CoinTop
import retrofit2.Response

class RetrofitRepository(
    val paprikaApi: PaprikaApi,
    val compareApi: CompareApi
):RetrofitInterface {

    override suspend fun getCoinHistory(id: String): List<Double> {

        Log.d("use_get_coin_history","start")
        Log.d("use_get_coin_history",id)
        val action = compareApi.getCryptoHistory(id,"USD",4)

        val data = action.body()

        val output:MutableList<Double> = ArrayList()

        Log.d("crypto_d",data.toString())

        data?.data?.data?.forEach {
            output.add(it!!.close!!)
        }


        Log.d("use_get_coin_history","code:${action.code()}")

        return output

    }

    override suspend fun getCoinInfo(id: String): CoinDetail {
        Log.d("use_get_coin_info","start")

        val action = paprikaApi.getCoinById(id)

        Log.d("use_get_coin_info","code:${action.code()}")

        return action.body()!!
    }

    override fun getCoinLogoBySymbol(sym: String): String {
        return "${CRYPTO_LOGO_URL}${sym.lowercase()}@2x.png"
    }

    override suspend fun getCoinsTopMarket(): Response<CoinTop> {
        return compareApi.getCryptoTop("USD",5)
    }


}