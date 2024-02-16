package com.example.weatherforecast.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.data.model.ForecastItem

class ForecastAdapter(private var forecastList: List<ForecastItem>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    fun setData(newForecastList: List<ForecastItem>) {
        forecastList = newForecastList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_forecast, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecastItem = forecastList[position]
        holder.bind(forecastItem)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val forecastTextView: TextView = itemView.findViewById(R.id.forecastItemTextView)

        fun bind(forecastItem: ForecastItem) {
            // Bind forecastItem data to UI
            forecastTextView.text = forecastItem.toString()
        }
    }
}
