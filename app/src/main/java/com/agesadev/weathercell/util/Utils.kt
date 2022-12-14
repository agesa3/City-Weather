package com.agesadev.weathercell.util

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.agesadev.weathercell.R
import com.agesadev.weathercell.model.CityWeatherPresentation
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

object Utils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun filterForecastBasedOnDate(
        list: List<CityWeatherPresentation>,
        daysToAdd: Long
    ): List<CityWeatherPresentation> {
        val dayAfterTomorrow = LocalDate.now().plusDays(daysToAdd).toString()
        return list.filter { it.dt_txt.contains(dayAfterTomorrow) }
    }

    fun getSunsetOrSunriseTime(sunset: Int): String {
        val date = Date(sunset.toLong() * 1000)
        val format = SimpleDateFormat("HH:mm")
        return format.format(date)

    }

    fun convertKelvinToDegrees(kelvin: Double): String {
        return String.format("%.1f", kelvin - 273.15) + "°C"
    }

    fun capitalizeTheFirstLetterOfTheWord(word: String): String {
        return word.substring(0, 1).uppercase(Locale.ROOT) + word.substring(1)
    }
}


fun View.showProgressBar(isVisible: Boolean): View {
    visibility = if (isVisible) View.VISIBLE else View.GONE
    return this
}


fun showSnackBarWithRetryAction(
    view: View,
    message: String,
    action: () -> Unit
) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        .setAction(R.string.retry) {
            action()
        }
        .show()
}





