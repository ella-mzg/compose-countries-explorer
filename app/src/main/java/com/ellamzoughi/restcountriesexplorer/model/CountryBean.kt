package com.ellamzoughi.restcountriesexplorer.model

data class CountryBean(
    val name: Name,
    val capital: List<String>?,
    val population: Int,
    val flags: Flags
)

data class Name(val common: String, val official: String)
data class Flags(val png: String, val svg: String)
