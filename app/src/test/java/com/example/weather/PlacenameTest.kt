package com.example.weather

import com.example.weather.data.local.dao.IForecastDao
import com.example.weather.data.local.entity.ForecastEntity
import com.example.weather.data.remote.placename.IPlacenameApi
import com.example.weather.data.remote.weatherapi.IWeatherApi
import com.example.weather.data.remote.weatherapi.dto.ForecastResponseDto
import com.example.weather.data.repository.WeatherRepositoryImpl
import com.example.weather.domain.repository.IWeatherRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlacenameTest {

    @Test
    fun testPlacenameAPI() = runBlocking {
        val name = "Huddinge"

        //Create test repo
        val placenameApi: IPlacenameApi = Retrofit.Builder()
            .baseUrl("https://geocode.maps.co/" )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IPlacenameApi::class.java)

        val weatherApi: IWeatherApi = object : IWeatherApi {
            override suspend fun getForecast(
                longitude: Float,
                latitude: Float
            ): ForecastResponseDto {
                return ForecastResponseDto(null,null,null,null,null)
            }
        }

        val dao: IForecastDao = object: IForecastDao {
            override suspend fun insert(forecast: ForecastEntity) {
                TODO("Not yet implemented")
            }

            override suspend fun getLatestForecast(
                name: String
            ): ForecastEntity? {
                return null
            }
        }

        val repository: IWeatherRepository = WeatherRepositoryImpl(
            placenameApi = placenameApi,
            weatherApi = weatherApi,
            dao = dao
        )

        val response = repository.getPlacenameRemote(name)

        if(response.isSuccess) {
            val placename = response.getOrNull()
            if(placename != null) println("Name: ${placename.name}, Longitude: ${placename.longitude}, Latitude: ${placename.latitude} ")
            else println("NULL")
        }
        else {
            println("ELSE")
        }


    }


}