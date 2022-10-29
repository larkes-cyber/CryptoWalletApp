package com.example.cryptowalletapp.data.retrofit.model

import androidx.core.view.ContentInfoCompat.Flags
import com.google.gson.annotations.SerializedName

data class CoinTop(

	@field:SerializedName("RateLimit")
	val rateLimitTop: RateLimitTop? = null,

	@field:SerializedName("Type")
	val type: Int? = null,

	@field:SerializedName("Message")
	val message: String? = null,

	@field:SerializedName("MetaData")
	val metaData: MetaData? = null,

	@field:SerializedName("HasWarning")
	val hasWarning: Boolean? = null,

	@field:SerializedName("Data")
	val data: List<DataItemTop?>? = null,

	@field:SerializedName("SponsoredData")
	val sponsoredData: List<Any?>? = null
)

data class DataItemTop(

	@field:SerializedName("DISPLAY")
	val dISPLAY: DISPLAY? = null,

	@field:SerializedName("RAW")
	val rAW: RAW? = null,

	@field:SerializedName("CoinInfo")
	val coinInfo: CoinInfo? = null
)

data class USD(

	@field:SerializedName("CONVERSIONTYPE")
	val cONVERSIONTYPE: String? = null,

	@field:SerializedName("LASTTRADEID")
	val lASTTRADEID: String? = null,

	@field:SerializedName("OPEN24HOUR")
	val oPEN24HOUR: String? = null,

	@field:SerializedName("HIGHDAY")
	val hIGHDAY: String? = null,

	@field:SerializedName("LOW24HOUR")
	val lOW24HOUR: String? = null,

	@field:SerializedName("TOPTIERVOLUME24HOUR")
	val tOPTIERVOLUME24HOUR: String? = null,

	@field:SerializedName("TOTALVOLUME24HTO")
	val tOTALVOLUME24HTO: String? = null,

	@field:SerializedName("TOSYMBOL")
	val tOSYMBOL: String? = null,

	@field:SerializedName("LASTVOLUME")
	val lASTVOLUME: String? = null,

	@field:SerializedName("LASTMARKET")
	val lASTMARKET: String? = null,

	@field:SerializedName("CIRCULATINGSUPPLY")
	val cIRCULATINGSUPPLY: String? = null,

	@field:SerializedName("LOWHOUR")
	val lOWHOUR: String? = null,

	@field:SerializedName("CONVERSIONSYMBOL")
	val cONVERSIONSYMBOL: String? = null,

	@field:SerializedName("MKTCAP")
	val mKTCAP: String? = null,

	@field:SerializedName("LASTUPDATE")
	val lASTUPDATE: String? = null,

	@field:SerializedName("CHANGEPCTHOUR")
	val cHANGEPCTHOUR: String? = null,

	@field:SerializedName("TOTALVOLUME24H")
	val tOTALVOLUME24H: String? = null,

	@field:SerializedName("VOLUMEHOURTO")
	val vOLUMEHOURTO: String? = null,

	@field:SerializedName("VOLUMEHOUR")
	val vOLUMEHOUR: String? = null,

	@field:SerializedName("TOPTIERVOLUME24HOURTO")
	val tOPTIERVOLUME24HOURTO: String? = null,

	@field:SerializedName("CHANGEDAY")
	val cHANGEDAY: String? = null,

	@field:SerializedName("FLAGS")
	val fLAGS: String? = null,

	@field:SerializedName("SUPPLY")
	val sUPPLY: String? = null,

	@field:SerializedName("MEDIAN")
	val mEDIAN: Double? = null,

	@field:SerializedName("TYPE")
	val tYPE: String? = null,

	@field:SerializedName("IMAGEURL")
	val iMAGEURL: String? = null,

	@field:SerializedName("VOLUMEDAY")
	val vOLUMEDAY: String? = null,

	@field:SerializedName("VOLUME24HOUR")
	val vOLUME24HOUR: String? = null,

	@field:SerializedName("MARKET")
	val mARKET: String? = null,

	@field:SerializedName("PRICE")
	val pRICE: String? = null,

	@field:SerializedName("CHANGEPCTDAY")
	val cHANGEPCTDAY: String? = null,

	@field:SerializedName("TOTALTOPTIERVOLUME24H")
	val tOTALTOPTIERVOLUME24H: String? = null,

	@field:SerializedName("FROMSYMBOL")
	val fROMSYMBOL: String? = null,

	@field:SerializedName("LASTVOLUMETO")
	val lASTVOLUMETO: String? = null,

	@field:SerializedName("CIRCULATINGSUPPLYMKTCAP")
	val cIRCULATINGSUPPLYMKTCAP: String? = null,

	@field:SerializedName("CHANGEPCT24HOUR")
	val cHANGEPCT24HOUR: String? = null,

	@field:SerializedName("OPENDAY")
	val oPENDAY: String? = null,

	@field:SerializedName("TOTALTOPTIERVOLUME24HTO")
	val tOTALTOPTIERVOLUME24HTO: String? = null,

	@field:SerializedName("VOLUMEDAYTO")
	val vOLUMEDAYTO: String? = null,

	@field:SerializedName("OPENHOUR")
	val oPENHOUR: String? = null,

	@field:SerializedName("CHANGE24HOUR")
	val cHANGE24HOUR: String? = null,

	@field:SerializedName("CHANGEHOUR")
	val cHANGEHOUR: String? = null,

	@field:SerializedName("HIGH24HOUR")
	val hIGH24HOUR: String? = null,

	@field:SerializedName("VOLUME24HOURTO")
	val vOLUME24HOURTO: String? = null,

	@field:SerializedName("LOWDAY")
	val lOWDAY: String? = null,

	@field:SerializedName("HIGHHOUR")
	val hIGHHOUR: String? = null,

	@field:SerializedName("MKTCAPPENALTY")
	val mKTCAPPENALTY: String? = null
)

data class Weiss(

	@field:SerializedName("Rating")
	val rating: String? = null,

	@field:SerializedName("TechnologyAdoptionRating")
	val technologyAdoptionRating: String? = null,

	@field:SerializedName("MarketPerformanceRating")
	val marketPerformanceRating: String? = null
)

data class CoinInfo(

	@field:SerializedName("Internal")
	val internal: String? = null,

	@field:SerializedName("Rating")
	val rating: Rating? = null,

	@field:SerializedName("BlockTime")
	val blockTime: Float? = null,

	@field:SerializedName("ImageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("MaxSupply")
	val maxSupply: Double? = null,

	@field:SerializedName("DocumentType")
	val documentType: String? = null,

	@field:SerializedName("Algorithm")
	val algorithm: String? = null,

	@field:SerializedName("Url")
	val url: String? = null,

	@field:SerializedName("Name")
	val name: String? = null,

	@field:SerializedName("Type")
	val type: Int? = null,

	@field:SerializedName("ProofType")
	val proofType: String? = null,

	@field:SerializedName("NetHashesPerSecond")
	val netHashesPerSecond: String? = null,

	@field:SerializedName("AssetLaunchDate")
	val assetLaunchDate: String? = null,

	@field:SerializedName("FullName")
	val fullName: String? = null,

	@field:SerializedName("Id")
	val id: String? = null,

	@field:SerializedName("BlockNumber")
	val blockNumber: Int? = null,

	@field:SerializedName("BlockReward")
	val blockReward: Double? = null
)

data class MetaData(

	@field:SerializedName("Count")
	val count: Int? = null
)

data class RateLimitTop(
	val any: Any? = null
)

data class RAW(

	@field:SerializedName("USD")
	val uSD: USD? = null
)

data class Rating(

	@field:SerializedName("Weiss")
	val weiss: Weiss? = null
)

data class DISPLAY(

	@field:SerializedName("USD")
	val uSD: USD? = null
)
