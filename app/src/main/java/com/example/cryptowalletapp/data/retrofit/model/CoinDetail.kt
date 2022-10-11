package com.example.cryptowalletapp.data.retrofit.model

data class CoinDetail(
	val symbol: String? = null,
	val isActive: Boolean? = null,
	val isNew: Boolean? = null,
	val proofType: String? = null,
	val firstDataAt: String? = null,
	val description: String? = null,
	val team: List<TeamItem?>? = null,
	val linksExtended: List<LinksExtendedItem?>? = null,
	val type: String? = null,
	val message: String? = null,
	val tags: List<TagsItem?>? = null,
	val lastDataAt: String? = null,
	val whitepaper: Whitepaper? = null,
	val orgStructure: String? = null,
	val hardwareWallet: Boolean? = null,
	val name: String? = null,
	val developmentStatus: String? = null,
	val hashAlgorithm: String? = null,
	val rank: Int? = null,
	val logo: String? = null,
	val startedAt: String? = null,
	val links: Links? = null,
	val id: String? = null,
	val openSource: Boolean? = null
)

data class TagsItem(
	val coinCounter: Int? = null,
	val icoCounter: Int? = null,
	val name: String? = null,
	val id: String? = null
)

data class Links(
	val youtube: List<String?>? = null,
	val website: List<String?>? = null,
	val facebook: List<String?>? = null,
	val explorer: List<String?>? = null,
	val reddit: List<String?>? = null,
	val sourceCode: List<String?>? = null
)

data class LinksExtendedItem(
	val type: String? = null,
	val url: String? = null,
	val stats: Stats? = null
)

data class Stats(
	val followers: Int? = null,
	val contributors: Int? = null,
	val stars: Int? = null,
	val subscribers: Int? = null
)

data class TeamItem(
	val name: String? = null,
	val id: String? = null,
	val position: String? = null
)

data class Whitepaper(
	val thumbnail: String? = null,
	val link: String? = null
)

