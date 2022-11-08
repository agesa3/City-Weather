package com.agesadev.data.repositories


import com.agesadev.domain.usecases.GetWeatherForecastUseCase
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
        fakeWeatherRepository = com.agesadev.data.repositories.FakeWeatherRepository()
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