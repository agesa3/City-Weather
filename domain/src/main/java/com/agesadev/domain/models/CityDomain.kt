package com.agesadev.domain.models

data class CityDomain(
    val coord: CoordDomain,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int,
)

