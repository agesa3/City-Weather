package com.agesadev.domain.models.moredays

import com.agesadev.domain.models.newmodel.CityDomain

data class WeatherForecastDomain(
    val city: CityDomain,
    val list: List<ForecastDomain>,
)
