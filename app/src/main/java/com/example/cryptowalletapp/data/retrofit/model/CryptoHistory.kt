package com.example.cryptowalletapp.data.retrofit.model

import com.google.gson.annotations.SerializedName

data class CryptoHistory(

	@field:SerializedName("Response")
	val response: String? = null,

	@field:SerializedName("RateLimit")
	val rateLimit: RateLimitTop? = null,

	@field:SerializedName("Type")
	val type: Int? = null,

	@field:SerializedName("Message")
	val message: String? = null,

	@field:SerializedName("HasWarning")
	val hasWarning: Boolean? = null,

	@field:SerializedName("Data")
	val data: Data? = null
)

data class DataItem(

	@field:SerializedName("high")
	val high: Double? = null,

	@field:SerializedName("low")
	val low: Double? = null,

	@field:SerializedName("conversionSymbol")
	val conversionSymbol: String? = null,

	@field:SerializedName("volumeto")
	val volumeto: Double? = null,

	@field:SerializedName("volumefrom")
	val volumefrom: Double? = null,

	@field:SerializedName("time")
	val time: Int? = null,

	@field:SerializedName("conversionType")
	val conversionType: String? = null,

	@field:SerializedName("close")
	val close: Double? = null,

	@field:SerializedName("open")
	val open: Double? = null
)

data class Data(

	@field:SerializedName("Aggregated")
	val aggregated: Boolean? = null,

	@field:SerializedName("TimeFrom")
	val timeFrom: Int? = null,

	@field:SerializedName("TimeTo")
	val timeTo: Int? = null,

	@field:SerializedName("Data")
	val data: List<DataItem?>? = null
)

data class RateLimit(
	val any: Any? = null
)
