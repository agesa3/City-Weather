package com.agesadev.domain.usecases

import com.agesadev.common.utils.Resource
import com.agesadev.data.repositories.FakeWeatherRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetWeatherForecastUseCaseTest {

    private lateinit var getWeatherForecastUseCase: GetWeatherForecastUseCase
    private lateinit var fakeWeatherRepository: FakeWeatherRepository

    @Before
    fun setup() {
        fakeWeatherRepository = FakeWeatherRepository()
        getWeatherForecastUseCase = GetWeatherForecastUseCase(fakeWeatherRepository)
    }


    @Test
    fun `getWeatherForecastUseCase should flow of Resource Sucess of ForecastWeatherDomain`() =
        runBlocking {
            val cityName = "Kisumu"
            assertEquals(cityName,fakeWeatherRepository.forecastWeatherDomain.city.name)
            assertEquals(cityName,getWeatherForecastUseCase(cityName).first().data?.city?.name)
        }
}