package com.agesadev.data.mappers

import com.agesadev.data.remote.dtos.*
import org.junit.Test

class DataLayerMappersTest {

    @Test
    fun `test that coord to coordDomain works`() {
        val coord = Coord(1.0, 2.0)
        val coordDomain = coord.toCoordDomain()
        assert(coordDomain.lat == coord.lat)
        assert(coordDomain.lon == coord.lon)
    }

    @Test
    fun `test that main to mainDomain mapper`() {
        val main = Main(1.0, 2, 3, 4, 5, 6.0, 2.0, 4.0, 4.4)
        val mainDomain = main.toMainDomain()
        assert(mainDomain.temp == main.temp)
    }

    @Test
    fun `assert that wind to windDomain mapper works`() {
        val wind = Wind(1, 2.0, 3.0)
        val windDomain = wind.toWindDomain()
        assert(windDomain.speed == wind.speed)
    }

    @Test
    fun `assert that clouds to cloudsDomain mapper works`() {
        val clouds = Clouds(1)
        val cloudsDomain = clouds.toCloudsDomain()
        assert(cloudsDomain.all == clouds.all)
    }

    @Test
    fun `assert that city weather dto to cityDomain mapper works`() {
        val city = City(Coord(1.0, 2.0), "KE", 1, "Kisumu", 0, 1, 1, 1)
        val cityDomain = city.toCityDomain()
        assert(cityDomain.name == city.name)
    }

    @Test
    fun `assert that forecast weather api response to weatherforecaset entity maps ok`() {
        val weatherForecastWeatherApiResponse = ForecastWeatherApiResponse(
            City(
                Coord(1.0, 2.0),
                "KE",
                1,
                "Kisumu",
                0,
                1,
                1,
                1
            ),
            1,
            "code",
            listOf(
                CityWeatherDto(
                    Clouds(1),
                    1,
                    "2021-01-01",
                    Main(1.0, 2, 3, 4, 5, 6.0, 2.0, 4.0, 4.4),
                    1.0,
                    Rain(1.0),
                    Sys("pod"),
                    1,
                    Wind(1, 2.0, 3.0)
                )
            ),
            1
        )
        val weatherForecastEntity = weatherForecastWeatherApiResponse.toWeatherForecastEntity()
        assert(weatherForecastEntity.city.name == weatherForecastWeatherApiResponse.city.name)
    }


}