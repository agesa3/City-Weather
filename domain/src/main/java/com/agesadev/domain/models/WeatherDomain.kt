package com.agesadev.domain.models


data class WeatherDomain(
    val clouds: CloudsDomain,
    val cod: Int,
    val coord: CoordDomain,
    val dt: Int,
    val id: Int,
    val main: MainDomain,
    val name: String,
    val sys: SysDomain,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherDomainLayer>,
    val wind: WindDomain
)
