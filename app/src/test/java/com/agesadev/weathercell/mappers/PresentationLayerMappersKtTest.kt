package com.agesadev.weathercell.mappers

import com.agesadev.data.remote.dtos.Main
import com.agesadev.domain.models.*
import com.agesadev.weathercell.model.*
import org.junit.Test


class PresentationLayerMappersKtTest {

    @Test
    fun `test CoordDomain to CoordPresentation`() {
        val coordDomain = CoordDomain(1.0, 2.0)
        val coordPresentation = coordDomain.toCoordPresentation()
        assert(coordPresentation.lat == 1.0)
        assert(coordPresentation.lon == 2.0)
    }


    @Test
    fun `test MainDomain to MainPresentation`() {
        val mainDomain = MainDomain(1.0, 2, 3, 4.0, 5.0, 6.0)
        val mainPresentation = mainDomain.toMainPresentation()
        assert(mainPresentation.temp == 4.0)
        assert(mainPresentation.feels_like == 1.0)
        assert(mainPresentation.temp_min == 6.0)
        assert(mainPresentation.temp_max == 5.0)
        assert(mainPresentation.pressure == 3)
        assert(mainPresentation.humidity == 2)
    }


    @Test
    fun `test WindDomain to WindPresentation`() {
        val windDomain = WindDomain(1.0, 2)
        val windPresentation = windDomain.toWindPresentation()
        assert(windPresentation.speed == 1.0)
        assert(windPresentation.deg == 2)
    }


    @Test
    fun `test CloudsDomain to CloudsPresentation`() {
        val cloudsDomain = CloudsDomain(1)
        val cloudsPresentation = cloudsDomain.toCloudsPresentation()
        assert(cloudsPresentation.all == 1)
    }


    @Test
    fun `test CityDomain to CityPresentation`() {
        val cityDomain = CityDomain(
            CoordDomain(1.0, 2.0),
            "KE",
            1,
            "kisumu",
            1,
            2,
            sunset = 3,
            timezone = 4
        )
        val cityPresentation = cityDomain.toCityPresentation()
        assert(cityPresentation.coord.lat == 1.0)
        assert(cityPresentation.coord.lon == 2.0)
        assert(cityPresentation.id == 1)
        assert(cityPresentation.name == "kisumu")
        assert(cityPresentation.country == "KE")
        assert(cityPresentation.sunrise == 2)
        assert(cityPresentation.sunset == 3)
    }


    @Test
    fun `test CityWeatherDomain to CityWeatherPresentation`() {
        val cityWeatherDomain = CityWeatherDomain(
            CloudsDomain(1),
            1,
            "dt_txt",
            MainDomain(1.0, 2, 3, 4.0, 5.0, 6.0),
            1.0,
            1,
            WindDomain(1.0, 2)
        )
        val cityWeatherPresentation = cityWeatherDomain.toCityWeatherPresentation()
        assert(cityWeatherPresentation.clouds.all == 1)
        assert(cityWeatherPresentation.dt == 1)
        assert(cityWeatherPresentation.dt_txt == "dt_txt")
        assert(cityWeatherPresentation.main.temp == 4.0)
        assert(cityWeatherPresentation.main.feels_like == 1.0)
        assert(cityWeatherPresentation.main.temp_min == 6.0)
        assert(cityWeatherPresentation.main.temp_max == 5.0)
        assert(cityWeatherPresentation.main.pressure == 3)
        assert(cityWeatherPresentation.main.humidity == 2)
        assert(cityWeatherPresentation.pop == 1.0)
        assert(cityWeatherPresentation.visibility == 1)
        assert(cityWeatherPresentation.wind.speed == 1.0)
        assert(cityWeatherPresentation.wind.deg == 2)
    }

    @Test
    fun `test ForecastWeatherDomain to ForecastWeather`() {
        val forecastWeatherDomain = ForecastWeatherDomain(
            CityDomain(
                CoordDomain(1.0, 2.0),
                "KE",
                1,
                "kisumu",
                1,
                2,
                sunset = 3,
                timezone = 4
            ),
            1,
            "cod",
            listOf(
                CityWeatherDomain(
                    CloudsDomain(1),
                    1,
                    "dt_txt",
                    MainDomain(1.0, 2, 3, 4.0, 5.0, 6.0),
                    1.0,
                    1,
                    WindDomain(1.0, 2)
                )
            ),
            1
        )
        val forecastWeather = forecastWeatherDomain.toForecastWeather()
        assert(forecastWeather.city.coord.lat == 1.0)
        assert(forecastWeather.city.coord.lon == 2.0)
        assert(forecastWeather.city.id == 1)
        assert(forecastWeather.city.name == "kisumu")
        assert(forecastWeather.city.country == "KE")
        assert(forecastWeather.city.sunrise == 2)
        assert(forecastWeather.city.sunset == 3)
        assert(forecastWeather.cnt == 1)
        assert(forecastWeather.cod == "cod")
        assert(forecastWeather.list.size == 1)
    }


}