package com.example.weather.di

import android.content.Context
import androidx.room.Room
import com.example.weather.data.local.dao.IForecastDao
import com.example.weather.data.local.db.ForecastDatabase
import com.example.weather.data.remote.placename.IPlacenameApi
import com.example.weather.data.remote.weatherapi.IWeatherApi
import com.example.weather.data.repository.WeatherRepositoryImpl
import com.example.weather.domain.repository.IWeatherRepository
import com.example.weather.domain.util.INetworkChecker
import com.example.weather.domain.util.NetworkChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val WEATHER_API = "https://api.open-meteo.com/"
    private const val PLACENAME_API = "https://geocode.maps.co/"

    //IWeatherApi - Dependency injection
    @Provides
    @Singleton
    fun provideWeatherApi(): IWeatherApi {
        return Retrofit.Builder()
            .baseUrl(WEATHER_API )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IWeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun providePlacenameApi(): IPlacenameApi {
        return Retrofit.Builder()
            .baseUrl(PLACENAME_API )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IPlacenameApi::class.java)
    }

    //IWeatherRepository
    @Provides
    @Singleton
    fun provideWeatherRepository(
        placenameApi: IPlacenameApi,
        weatherApi: IWeatherApi,
        dao: IForecastDao
    ): IWeatherRepository {
        return WeatherRepositoryImpl(
            weatherApi = weatherApi,
            placenameApi = placenameApi,
            dao = dao
        )
    }

    @Provides
    @Singleton
    fun provideNetworkChecker(
        @ApplicationContext context: Context
    ): INetworkChecker {
        return NetworkChecker(context)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): ForecastDatabase{
        return Room.databaseBuilder(
            appContext,
            ForecastDatabase::class.java,
            "forecast_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideForecastDao(db: ForecastDatabase): IForecastDao{
        return db.forecastDao()
    }
}