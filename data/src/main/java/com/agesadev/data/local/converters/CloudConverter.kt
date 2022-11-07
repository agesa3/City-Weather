package com.agesadev.data.local.converters

import androidx.room.TypeConverter
import com.agesadev.data.remote.model.Clouds

class CloudConverter {


    @TypeConverter
    fun fromCloud(clouds: Clouds): String {
        return clouds.all.toString()
    }

    @TypeConverter
    fun toCloud(all: String): Clouds {
        return Clouds(all.toInt())
    }
}