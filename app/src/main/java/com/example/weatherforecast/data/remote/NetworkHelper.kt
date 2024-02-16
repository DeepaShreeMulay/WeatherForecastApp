package com.example.weatherforecast.data.remote

import com.example.weatherforecast.data.utils.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkHelper {
    companion object{

        fun getRetrofitInstance(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }

        fun getWeatherForecastApi(retrofit: Retrofit): WeatherApiService =
            retrofit.create(WeatherApiService::class.java)
    }
}