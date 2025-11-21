package com.example.weather.di

import com.example.weather.data.remote.IWeatherApi
import com.example.weather.data.repository.WeatherRepositoryImpl
import com.example.weather.domain.repository.IWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val API_BASE_URL = "https://maceo.sth.kth.se/"

    //IWeatherApi - Dependency injection
    @Provides
    @Singleton
    fun provideWeatherApi(): IWeatherApi {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IWeatherApi::class.java)
    }

    //IWeatherRepository
    @Provides
    @Singleton
    fun provideWeatherRepository(api: IWeatherApi): IWeatherRepository {
        return WeatherRepositoryImpl(api)
    }
}