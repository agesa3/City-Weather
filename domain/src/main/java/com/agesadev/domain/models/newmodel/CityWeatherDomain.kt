package com.agesadev.domain.models.newmodel

import com.agesadev.domain.models.CloudsDomain
import com.agesadev.domain.models.MainDomain
import com.agesadev.domain.models.WindDomain

data class CityWeatherDomain(
    val clouds: CloudsDomain,
    val dt: Int,
    val dt_txt: String,
    val main: MainDomain,
    val pop: Double,
    val visibility: Int,
    val wind: WindDomain
)