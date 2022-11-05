package com.agesadev.weathercell.model

import com.agesadev.data.remote.models.*

data class WeatherPresentation(
    val coord: CoordPresentation,
    val weather: List<WeatherData>,
    val main: MainPresentation,
    val visibility: Int,
    val wind: WindPresentation,
    val clouds: CloudsPresentation,
    val dt: Int,
    val sys: SysPresentation,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)
