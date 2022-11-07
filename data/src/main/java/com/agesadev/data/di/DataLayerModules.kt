package com.agesadev.data.di

import android.content.Context
import androidx.room.Room
import com.agesadev.common.Constants.BASE_URL
import com.agesadev.common.Constants.DB_NAME
import com.agesadev.data.BuildConfig
import com.agesadev.data.local.dao.WeatherDao
import com.agesadev.data.local.db.WeatherDatabase
import com.agesadev.data.remote.WeatherApi
import com.agesadev.data.repositories.WeatherRepositoriesImpl
import com.agesadev.domain.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataLayerModules {


    @Provides
    @Singleton
    fun providesOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesOkHttpClient())
            .build()
    }

    @Provides
    @Singleton
    fun providesWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherApi: WeatherApi,
        weatherDao: WeatherDao
    ): WeatherRepository {
        return WeatherRepositoriesImpl(weatherApi, weatherDao)
    }

    @Provides
    @Singleton
    fun providesWeatherDatabase(@ApplicationContext applicationContext: Context): WeatherDatabase {
        return Room.databaseBuilder(
            applicationContext,
            WeatherDatabase::class.java,
            DB_NAME
        ).build()
    }


    @Provides
    fun providesWeatherDao(weatherDatabase: WeatherDatabase) = weatherDatabase.weatherDao()


}