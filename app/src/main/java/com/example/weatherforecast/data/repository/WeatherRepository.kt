package com.example.weatherforecast.data.repository

import com.example.weatherforecast.data.model.ForecastResponse
import com.example.weatherforecast.data.model.WeatherResponse
import com.example.weatherforecast.data.remote.NetworkHelper
import com.example.weatherforecast.data.remote.WeatherApiService
import io.reactivex.rxjava3.core.Single

class WeatherRepository {

    private val weatherApiService: WeatherApiService

    init {
        val retrofit = NetworkHelper.getRetrofitInstance()
        weatherApiService = NetworkHelper.getWeatherForecastApi(retrofit)
    }

    fun getCurrentWeather(lat: Double, lon: Double, apiKey: String): Single<WeatherResponse> {
        return weatherApiService.getCurrentWeather(lat, lon, apiKey)
    }

    fun getWeatherForecast(lat: Double, lon: Double, apiKey: String): Single<ForecastResponse> {
        return weatherApiService.getWeatherForecast(lat, lon, apiKey)
    }
}

