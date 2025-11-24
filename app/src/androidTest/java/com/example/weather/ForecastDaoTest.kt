package com.example.weather

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.weather.data.local.converter.ForecastConverter
import com.example.weather.data.local.dao.IForecastDao
import com.example.weather.data.local.db.ForecastDatabase
import com.example.weather.data.local.entity.ForecastEntity
import com.example.weather.domain.models.Forecast
import com.example.weather.domain.models.Weather
import com.example.weather.domain.models.WeatherIcon
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class ForecastDaoTest {

    private lateinit var dao: IForecastDao
    private lateinit var db: ForecastDatabase

    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ForecastDatabase::class.java).build()
        dao = db.forecastDao()
    }

    @After
    @Throws(Exception::class)
    fun closeDb(){
        db.close()
    }

    @Test
    fun writeAndReadForecast() = runBlocking {
        val forecast = Forecast(
            longitude = 17.555f,
            latitude = 103.383f,
            currentWeather = Weather.Current(
                time = LocalDateTime.now(),
                weatherIcon = WeatherIcon.Cloud,
                temperature = 100f
            ),
            hourlyWeather = listOf(
                Weather.Hourly(
                    time = LocalDateTime.now().plusHours(1),
                    weatherIcon = WeatherIcon.Sun,
                    temperature = 200f
                )
            ),
            dailyWeather = listOf(
                Weather.Daily(
                    time = LocalDateTime.now().plusHours(10),
                    weatherIcon = WeatherIcon.CloudSun,
                    maxTemperature = 500f,
                    minTemperature = -100f
                )
            )
        )

        val forecastEntity = ForecastEntity(
            longitude = forecast.longitude,
            latitude = forecast.latitude,
            timestamp = System.currentTimeMillis(),
            forecastJson = ForecastConverter.fromForecast(forecast)
        )

        dao.insert(forecastEntity)

        val fetched = dao.getLatestForecast(17.555f, 103.383f)
        println("$fetched")
    }
}

/*package com.example.weather.domain.models

import com.example.weather.domain.models.WeatherIcon
import java.time.LocalDateTime

sealed class Weather{
    abstract val time: LocalDateTime
    abstract val weatherIcon: WeatherIcon

    data class Current(
        override val time: LocalDateTime,
        override val weatherIcon: WeatherIcon,
        val temperature: Float
    ) : Weather()

    data class Hourly(
        override val time: LocalDateTime,
        override val weatherIcon: WeatherIcon,
        val temperature: Float
    ) : Weather()

    data class Daily(
        override val time: LocalDateTime,
        override val weatherIcon: WeatherIcon,
        val maxTemperature: Float,
        val minTemperature: Float
    ) : Weather()
}

 */