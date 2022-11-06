package com.agesadev.data.repositories

import android.util.Log
import com.agesadev.common.utils.Resource
import com.agesadev.data.local.dao.WeatherDao
import com.agesadev.data.local.model.toWeatherForecastDomain
import com.agesadev.data.local.model.toWeatherForecastEntity
import com.agesadev.data.mappers.toCityForeCast
import com.agesadev.data.mappers.toForecastDomain
import com.agesadev.data.mappers.toWeatherDomain
import com.agesadev.data.remote.WeatherApi
import com.agesadev.domain.models.WeatherDomain
import com.agesadev.domain.models.moredays.WeatherForecastDomain
import com.agesadev.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class WeatherRepositoriesImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao
) : WeatherRepository {
    override fun getCityWeatherByCityName(cityName: String): Flow<Resource<WeatherDomain>> = flow {
        val weatherFromDb = weatherDao.getWeatherForecastByCityName(cityName)
        Log.d("Repository", "getCityWeatherByCityName: $weatherFromDb and cityName: $cityName")
        if (weatherFromDb != null) {
            emit(Resource.Success(weatherFromDb.toWeatherForecastDomain()))
            Log.d("Repository2", "getCityWeatherByCityName: $weatherFromDb and cityName: $cityName")
        } else {
            try {
                val response = weatherApi.getWeatherByCityName(cityName)
                val weatherEntity = response.toWeatherForecastEntity()
                weatherEntity.let {
                    weatherDao.insertWeatherForecast(it)
                }
                val weatherDomain = weatherEntity.toWeatherForecastDomain()
                emit(Resource.Success(weatherDomain))
            } catch (e: IOException) {
                emit(Resource.Error(e.message.toString()))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message().toString()))
            }
        }
    }

    override fun getCityWeatherByLatLon(
        lat: Double,
        lon: Double
    ): Flow<Resource<List<WeatherDomain>>> = flow {
        val weatherFromDb = weatherDao.getWeatherForecastByLatLon(lat, lon)
        if (weatherFromDb != null) {
            emit(Resource.Success(weatherFromDb.map { it.toWeatherForecastDomain() }))
        } else {
            try {
                val response = weatherApi.getWeatherByCityCoord(lat, lon).list
                val saveResponseToDb= weatherDao.insertWeatherForecast(response)


            } catch (e: IOException) {
                emit(Resource.Error(e.message.toString()))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message().toString()))
            }
        }
    }


}