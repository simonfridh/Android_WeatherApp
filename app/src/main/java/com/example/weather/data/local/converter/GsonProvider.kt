package com.example.weather.data.local.converter

import com.example.weather.domain.models.WeatherIcon
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GsonProvider {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    val gson: Gson = GsonBuilder()
        .registerTypeAdapter(
            LocalDateTime::class.java, object : JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
                override fun serialize(
                    src: LocalDateTime?,
                    typeOfSrc: Type?,
                    context: JsonSerializationContext?
                ): JsonElement {
                    return JsonPrimitive(src?.format(formatter))
            }
            override fun deserialize(
                json: JsonElement?,
                typeOfT: Type?,
                context: JsonDeserializationContext?
            ): LocalDateTime {
                return LocalDateTime.parse(json?.asString, formatter)
            }
        }
        )
        .registerTypeAdapter(
            WeatherIcon::class.java, object : JsonSerializer<WeatherIcon>, JsonDeserializer<WeatherIcon>{
                override fun serialize(
                    src: WeatherIcon?,
                    typeOfSrc: Type?,
                    context: JsonSerializationContext?
                ):JsonElement {
                    val code = src?.let { WeatherIcon.codeFromWeatherIcon(it) } ?: -1
                    return JsonPrimitive(code)
                }

                override fun deserialize(
                    json: JsonElement?,
                    typeOfT: Type?,
                    context: JsonDeserializationContext?
                ): WeatherIcon {
                    val code = json?.asInt ?: -1
                    return WeatherIcon.iconFromWeatherCode(code)
                }
            }
        )
        .create()
}