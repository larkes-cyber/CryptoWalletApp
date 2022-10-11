package com.example.cryptowalletapp.data.retrofit.model

data class CryptoHistory(
	val response: String? = null,
	val rateLimit: RateLimit? = null,
	val type: Int? = null,
	val message: String? = null,
	val hasWarning: Boolean? = null,
	val data: Data? = null
)

data class DataItem(
	val high: Double? = null,
	val low: Double? = null,
	val conversionSymbol: String? = null,
	val volumeto: Double? = null,
	val volumefrom: Double? = null,
	val time: Int? = null,
	val conversionType: String? = null,
	val close: Double? = null,
	val open: Double? = null
)

data class Data(
	val aggregated: Boolean? = null,
	val timeFrom: Int? = null,
	val timeTo: Int? = null,
	val data: List<DataItem?>? = null
)

data class RateLimit(
	val any: Any? = null
)

