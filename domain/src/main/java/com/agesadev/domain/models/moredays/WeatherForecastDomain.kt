package com.agesadev.domain.models.moredays

data class WeatherForecastDomain(
    val city: CityDomain,
    val list: List<ForecastDomain>,
)
