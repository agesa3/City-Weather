package com.agesadev.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.agesadev.data.local.dao.WeatherDao
import com.agesadev.data.local.model.WeatherForecastEntity
import com.agesadev.data.remote.dtos.*
import com.agesadev.domain.models.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class WeatherDaoTest {

    private lateinit var weatherDatabase: WeatherDatabase
    private lateinit var weatherDao: WeatherDao

    @Before
    fun setup() {
        weatherDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherDatabase::class.java
        ).allowMainThreadQueries().build()

        weatherDao = weatherDatabase.weatherDao()
    }


    @Test
    fun insertWeatherForecast() = runBlocking {
        val weatherForecastEntity = WeatherForecastEntity(
            city = City(
                id = 0,
                name = "Kisumu",
                coord = Coord(lat = 0.0, lon = 0.0),
                country = "EG",
                population = 0,
                timezone = 0,
                sunrise = 0,
                sunset = 0
            ),
            cnt = 0,
            cod = "200",
            list = listOf(
                CityWeatherDto(
                    dt = 0, main = Main(
                        temp = 0.0,
                        feels_like = 0.0,
                        temp_min = 0.0,
                        temp_max = 0.0,
                        pressure = 0,
                        humidity = 0,
                        grnd_level = 0,
                        sea_level = 0,
                        temp_kf = 0.0
                    ),
                    clouds = Clouds(
                        all = 0
                    ),
                    wind = Wind(
                        speed = 0.0,
                        deg = 0,
                        gust = 0.0
                    ),
                    visibility = 0,
                    pop = 0.0,
                    dt_txt = "2021-09-01 12:00:00",
                    rain = Rain(
                        `3h` = 0.0
                    ),
                    sys = Sys(
                        pod = "d"
                    )

                )
            ),
            message = 0,
            id = 2
        )

        weatherDao.insertWeatherForecast(weatherForecastEntity)
        val allWeatherForecast = weatherDao.getWeatherForecastByCityName("Kisumu")
        assert(allWeatherForecast.city.name == "Kisumu")

    }


    @Test
    fun deleteWeatherForeCastFromDb() = runBlocking {
        val weatherForecastEntity = WeatherForecastEntity(
            city = City(
                id = 0,
                name = "Kisumu",
                coord = Coord(lat = 0.0, lon = 0.0),
                country = "EG",
                population = 0,
                timezone = 0,
                sunrise = 0,
                sunset = 0
            ),
            cnt = 0,
            cod = "200",
            list = listOf(
                CityWeatherDto(
                    dt = 0, main = Main(
                        temp = 0.0,
                        feels_like = 0.0,
                        temp_min = 0.0,
                        temp_max = 0.0,
                        pressure = 0,
                        humidity = 0,
                        grnd_level = 0,
                        sea_level = 0,
                        temp_kf = 0.0
                    ),
                    clouds = Clouds(
                        all = 0
                    ),
                    wind = Wind(
                        speed = 0.0,
                        deg = 0,
                        gust = 0.0
                    ),
                    visibility = 0,
                    pop = 0.0,
                    dt_txt = "2021-09-01 12:00:00",
                    rain = Rain(
                        `3h` = 0.0
                    ),
                    sys = Sys(
                        pod = "d"
                    )

                )
            ),
            message = 0,
            id = 2
        )

        weatherDao.insertWeatherForecast(weatherForecastEntity)
        weatherDao.deleteAllWeatherForecast()
        val allWeatherForecast = weatherDao.getWeatherForecastByCityName("Kisumu")
        assert(allWeatherForecast == null)

    }


    @After
    fun teardown() {
        weatherDatabase.close()
    }

}