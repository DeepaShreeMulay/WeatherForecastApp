package com.example.weatherforecast.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.data.model.ForecastResponse
import com.example.weatherforecast.data.model.WeatherResponse
import com.example.weatherforecast.data.repository.WeatherRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    val currentWeatherLiveData: MutableLiveData<WeatherResponse> = MutableLiveData()
    val weatherForecastLiveData: MutableLiveData<ForecastResponse> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun getCurrentWeather(lat: Double, lon: Double, apiKey: String) {
        repository.getCurrentWeather(lat, lon, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                currentWeatherLiveData.value = response
            }, { error ->
                errorLiveData.value = error.message
            })
    }

    fun getWeatherForecast(lat: Double, lon: Double, apiKey: String) {
        repository.getWeatherForecast(lat, lon, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                weatherForecastLiveData.value = response
            }, { error ->
                errorLiveData.value = error.message
            })
    }
}



