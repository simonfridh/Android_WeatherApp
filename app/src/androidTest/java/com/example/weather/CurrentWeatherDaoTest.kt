package com.example.weather

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.weather.data.local.dao.CurrentWeatherDao
import com.example.weather.data.local.db.WeatherDatabase
import com.example.weather.data.local.entity.CurrentWeatherEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class CurrentWeatherDaoTest {

    private lateinit var dao: CurrentWeatherDao
    private lateinit var db: WeatherDatabase

    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, WeatherDatabase::class.java).build()
        dao = db.currentWeatherDao()
    }

    @After
    @Throws(Exception::class)
    fun closeDb(){
        db.close()
    }

    @Test
    fun writeAndReadCurrentWeather() = runBlocking {
        val currentWeather = CurrentWeatherEntity(
            longitude = 17.555,
            latitude = 103.383,
            time = System.currentTimeMillis(),
            temperature = -5.1f,
            weatherIcon = 1
        )

        dao.insert(currentWeather)

        val fetched = dao.getCurrent(17.555, 103.383)
        println("$fetched")
    }
}