package com.example.weatherforecast.data.utils

import java.text.Format
import java.text.SimpleDateFormat
import java.util.Date

object DateTime {
    fun convertLongToDateTime(time : Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd/MM/yyyy hh:mm aa")
        return format.format(date)
    }
    fun convertDateToDay(time : Long): String {
        val timeFormat: Format = SimpleDateFormat("EEEE")
        val day : String = timeFormat.format(time)
        return day
    }
    fun convertDateToLong(date : String): Long {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        return dateFormat.parse(date).time
    }
}