package com.example.weatherforecast.ui.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.data.factory.WeatherViewModelFactory
import com.example.weatherforecast.data.repository.WeatherRepository
import com.example.weatherforecast.data.utils.Constants
import com.example.weatherforecast.data.utils.DateTime
import com.example.weatherforecast.ui.adapters.ForecastAdapter
import com.example.weatherforecast.ui.viewmodels.WeatherViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var forecastAdapter: ForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weatherRepository = WeatherRepository()

        val weatherViewModelFactory = WeatherViewModelFactory(weatherRepository)
        weatherViewModel = ViewModelProvider(this, weatherViewModelFactory).get(WeatherViewModel::class.java)


        // Initialize RecyclerView
        forecastAdapter = ForecastAdapter(emptyList())
        val forecastRecyclerView = findViewById<RecyclerView>(R.id.forecastRecyclerView)
        forecastRecyclerView.layoutManager = LinearLayoutManager(this)
        forecastRecyclerView.adapter = forecastAdapter

        // Observe LiveData for current weather
        weatherViewModel.currentWeatherLiveData.observe(this) { currentWeather ->
            // Update UI with current weather details
            val dateTextView = findViewById<TextView>(R.id.dateTextView)
            dateTextView.text = DateTime.convertLongToDateTime(currentWeather.dt)
            val mainTextView = findViewById<TextView>(R.id.mainTextView)
            mainTextView.text = currentWeather.weather[0].main

        }

        // Observe LiveData for weather forecast
        weatherViewModel.weatherForecastLiveData.observe(this) { forecastList ->
            // Update RecyclerView with forecast data
            forecastAdapter.setData(forecastList.list)
        }

        // Fetch weather data
        val lat = 44.34
        val lon = 10.99
        val apiKey = Constants.API_KEY
        weatherViewModel.getCurrentWeather(lat, lon, apiKey)
        weatherViewModel.getWeatherForecast(lat, lon, apiKey)
    }
}

