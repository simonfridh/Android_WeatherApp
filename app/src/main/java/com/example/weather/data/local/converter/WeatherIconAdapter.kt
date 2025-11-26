package com.example.weather.data.local.converter

import com.example.weather.domain.models.WeatherIcon
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

// TODO: Delete, moved code to GsonProvider
class WeatherIconAdapter : JsonSerializer<WeatherIcon>, JsonDeserializer<WeatherIcon>{

    override fun serialize(
        src: WeatherIcon?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val code = src?.let { WeatherIcon.codeFromWeatherIcon(it)} ?:-1
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