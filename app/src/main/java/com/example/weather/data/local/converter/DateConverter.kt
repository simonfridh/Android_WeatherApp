package com.example.weather.data.local.converter

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset

object DateConverter {
    @TypeConverter
    fun fromLocalDateTime(date: LocalDateTime?): Long? {
        return date?.toEpochSecond(ZoneOffset.UTC)
    }
    @TypeConverter
    fun toLocalDateTime(time: Long?): LocalDateTime? {
        return time?.let {LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC)}
    }
}