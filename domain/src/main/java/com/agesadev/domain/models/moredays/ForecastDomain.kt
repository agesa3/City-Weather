package com.agesadev.domain.models.moredays

import com.agesadev.domain.models.*

data class ForecastDomain(
    val clouds: CloudsDomain,
    val dt: Int,
    val dt_txt: String,
    val main: MainDomain,
    val pop: Double,
    val sys: SysDomain,
    val visibility: Int,
    val weather: List<WeatherDomainLayer>,
    val wind: WindDomain
)