package com.agesadev.data.local.converters

import androidx.room.TypeConverter
import com.agesadev.data.remote.model.*
import com.google.gson.Gson

class ListStringCityWeather {


    @TypeConverter
    fun listStringToString(list: List<CityWeatherDto>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToListString(str: String?): List<CityWeatherDto> {
        val gson = Gson()
        return gson.fromJson(str, Array<CityWeatherDto>::class.java).toList()
    }
//    data class Coord(
//        val lat: Double,
//        val lon: Double
//    )

    @TypeConverter
    fun fromCoord(coord: Coord): String {
        val gson = Gson()
        return gson.toJson(coord)
    }

    @TypeConverter
    fun toCoord(str: String): Coord {
        val gson = Gson()
        return gson.fromJson(str, Coord::class.java)
    }

//    @TypeConverter
//    fun fromCoordinates(coord: Coord):String{
//        return coord.toString()
//    }

//    @TypeConverter
//    fun toCoodinates(stringCoord: String){
//        return Coord()
//    }

}



