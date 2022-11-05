package com.agesadev.domain.models

data class WeatherDomainLayer(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)