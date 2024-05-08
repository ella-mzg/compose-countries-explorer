package com.ellamzoughi.restcountriesexplorer.model

data class CountryBean(
    val name: Name,
    val capital: List<String>?,
    val population: Int,
    val flag: String
)

data class Name(val common: String, val official: String)
