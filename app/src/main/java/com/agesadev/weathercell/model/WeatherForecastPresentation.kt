package com.agesadev.weathercell.model

data class WeatherForecastPresentation(
    val city: CityPresentation,
    val list: List<CityWeatherPresentation>,
)

data class CityWeatherPresentation(
    val clouds: CloudsPresentation,
    val dt: Int,
    val dt_txt: String,
    val main: MainPresentation,
    val pop: Double,
    val sys: SysPresentation,
    val visibility: Int,
    val weather: List<WeatherData>,
    val wind: WindPresentation
)