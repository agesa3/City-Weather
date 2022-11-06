package com.agesadev.data.repositories

import com.agesadev.common.utils.Resource
import com.agesadev.data.mappers.toCityForeCast
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
    private val weatherApi: WeatherApi
) : WeatherRepository {
    override fun getCityWeatherByCityName(cityName: String): Flow<Resource<WeatherDomain>> = flow {
        try {
            val weatherFromApi = weatherApi.getWeatherByCityName(cityName)
            emit(Resource.Success(weatherFromApi.toWeatherDomain()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getCityWeatherByLatLon(
        lat: Double,
        lon: Double
    ): Flow<Resource<WeatherForecastDomain>> = flow {
        try {
            val weatherForecastFromApi = weatherApi.getWeatherByCityCoord(lat, lon)
            emit(Resource.Success(weatherForecastFromApi.toCityForeCast()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }

    }

}