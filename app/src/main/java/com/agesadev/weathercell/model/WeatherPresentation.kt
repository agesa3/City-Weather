package com.agesadev.weathercell.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class WeatherPresentation(
    val coord: @RawValue CoordPresentation,
    val weather: @RawValue List<WeatherData>,
    val main: @RawValue MainPresentation,
    val visibility: Int,
    val wind: @RawValue WindPresentation,
    val clouds: @RawValue CloudsPresentation,
    val dt: Int,
    val sys: @RawValue SysPresentation,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
) : Parcelable
