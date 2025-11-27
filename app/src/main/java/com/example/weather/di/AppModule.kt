package com.example.weather.di

import android.content.Context
import androidx.room.Room
import com.example.weather.data.local.dao.IForecastDao
import com.example.weather.data.local.db.ForecastDatabase
import com.example.weather.data.remote.IWeatherApi
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
    private const val API_BASE_URL = "https://api.open-meteo.com/"

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
    fun provideWeatherRepository(
        api: IWeatherApi,
        dao: IForecastDao
    ): IWeatherRepository {
        return WeatherRepositoryImpl(api, dao)
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