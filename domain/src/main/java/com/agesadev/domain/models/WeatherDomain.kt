package com.agesadev.domain.models

data class WeatherDomain(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
