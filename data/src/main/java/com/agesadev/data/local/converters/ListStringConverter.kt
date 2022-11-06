package com.agesadev.data.local.converters

import androidx.room.TypeConverter

class ListStringConverter {
    @TypeConverter
    fun listStringToString(list: List<String>?): String {
        return list?.joinToString(separator = SPLITTER) ?: ""
    }

    @TypeConverter
    fun stringToListString(str: String?): List<String> {
        return str?.split(SPLITTER) ?: emptyList()
    }

    companion object {
        private const val SPLITTER = ","
    }
}