package com.agesadev.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.agesadev.data.local.converters.ListStringCityWeather
import com.agesadev.data.local.converters.ListStringConverter
import com.agesadev.data.local.dao.WeatherDao
import com.agesadev.data.local.model.WeatherForecastEntity

@Database(entities = [WeatherForecastEntity::class], version = 1)
@TypeConverters(ListStringConverter::class,ListStringCityWeather::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}