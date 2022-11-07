package com.agesadev.data.repositories

import com.agesadev.common.utils.Resource
import com.agesadev.data.local.dao.WeatherDao
import com.agesadev.data.local.model.toWeatherForeCastDomain
import com.agesadev.data.mappers.toWeatherForecastEntity
import com.agesadev.data.remote.WeatherApi
import com.agesadev.domain.models.newmodel.ForecastWeatherDomain
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
    override fun getCityWeatherByCityName(cityName: String): Flow<Resource<ForecastWeatherDomain>> =
        flow {
            val weatherFromDb = weatherDao.getWeatherForecastByCityName(cityName)
            if (weatherFromDb != null) {
                emit(Resource.Success(weatherFromDb.toWeatherForeCastDomain()))
            }
            try {
                val weatherFromApi = weatherApi.getWeatherForecast(cityName)
                weatherDao.insertWeatherForecast(weatherFromApi.toWeatherForecastEntity())
                val weatherFromDb = weatherDao.getWeatherForecastByCityName(cityName)
                emit(Resource.Success(weatherFromDb.toWeatherForeCastDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(e.message.toString()))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message.toString()))
            }
        }
}
