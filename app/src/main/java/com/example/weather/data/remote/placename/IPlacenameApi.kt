package com.example.weather.data.remote.placename

import com.example.weather.data.remote.placename.dto.PlacenameDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IPlacenameApi {
    @GET("search?api_key=69272de7b132e669366158yvff3c008")
    suspend fun getForecast(
        @Query("q") address: String,
    ): List<PlacenameDto>
}