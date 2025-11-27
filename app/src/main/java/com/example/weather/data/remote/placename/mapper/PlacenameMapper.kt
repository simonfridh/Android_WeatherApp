package com.example.weather.data.remote.placename.mapper

import com.example.weather.data.remote.placename.dto.PlacenameDto
import com.example.weather.domain.models.placename.Placename

fun List<PlacenameDto>.toPlacename(): Placename {
    if(this.isEmpty()) throw Exception("Response is empty")
    val firstPlacename = this.first()

    //Check if fields are null
    if(firstPlacename.name == null || firstPlacename.lon == null || firstPlacename.lat == null){
        throw Exception("First placename has null fields")
    }

    return Placename(
        name = firstPlacename.name,
        longitude = firstPlacename.lon.toFloat(),
        latitude = firstPlacename.lat.toFloat()
    )
}