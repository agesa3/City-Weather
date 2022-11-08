package com.agesadev.weathercell.util

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.agesadev.data.remote.dtos.Wind
import com.agesadev.weathercell.R
import com.agesadev.weathercell.model.CityWeatherPresentation
import com.agesadev.weathercell.model.CloudsPresentation
import com.agesadev.weathercell.model.MainPresentation
import com.agesadev.weathercell.model.WindPresentation
import com.agesadev.weathercell.util.Utils.getSunsetOrSunriseTime
import com.google.android.material.snackbar.Snackbar
import org.junit.Test
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class UtilsTest {


    @Test
    fun `getSunsetOrSunriseTime should return correct time`() {
        val sunset = 1620000000
        val date = Date(sunset.toLong() * 1000)
        val format = SimpleDateFormat("HH:mm")
        val expected = format.format(date)
        val actual = getSunsetOrSunriseTime(sunset)
        assert(expected == actual)
    }


    @Test
    fun `convertKelvinToDegrees should return correct degrees`() {
        val kelvin = 300.0
        val expected = "26.9°C"
        val actual = Utils.convertKelvinToDegrees(kelvin)
        assert(expected == actual)
    }


}