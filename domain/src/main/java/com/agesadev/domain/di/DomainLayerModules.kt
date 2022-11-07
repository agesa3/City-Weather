package com.agesadev.domain.di

import com.agesadev.domain.repositories.WeatherRepository
import com.agesadev.domain.usecases.GetWeatheForecastUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainLayerModules {

    @Provides
    @Singleton
    fun providesWeatherUseCase(weatherRepository: WeatherRepository) =
        GetWeatheForecastUsecase(weatherRepository)
}