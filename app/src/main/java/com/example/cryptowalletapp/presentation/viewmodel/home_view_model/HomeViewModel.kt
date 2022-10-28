package com.example.cryptowalletapp.presentation.viewmodel.home_view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptowalletapp.domain.model.CoinInfo
import com.example.cryptowalletapp.domain.usecase.UseGetCurrencyLogoBySym
import com.example.cryptowalletapp.domain.usecase.UseGetSmallCoinInfo
import java.lang.Double.max

import java.lang.Math.min
import java.math.RoundingMode
import java.text.DecimalFormat

class HomeViewModel(
    val useGetSmallCoinInfo: UseGetSmallCoinInfo,
    val useGetCurrencyLogoBySym: UseGetCurrencyLogoBySym
):ViewModel() {

    private var lifeFirstCurrencyResult = MutableLiveData<CoinInfo>()
    val lifeFirstCurrencyResultConst = lifeFirstCurrencyResult

    suspend fun getSmallCoinInfo(id:String){
        lifeFirstCurrencyResult.value = useGetSmallCoinInfo.execute(id)
    }

    fun geyLogo(syb:String):String{
        return useGetCurrencyLogoBySym.execute(syb)
    }

    fun findPerc(num1:Int, num2:Int):String{
        val perc = (100 * num2).toDouble() / num1
        return roundForPerc(max(100.0,perc) - 100.0.coerceAtMost(perc))!!
    }

    fun roundOffDecimal(number: Double): String? {
        return "%.7f".format(number)
    }

    fun roundForPerc(number: Double): String? {
        return "%.2f".format(number)
    }

    fun getPrice(num:Int):String{
        return roundOffDecimal(1.0 / num)!!
    }


}