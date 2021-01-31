package com.nab.assignment.ui.weather

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nab.assignment.R
import com.nab.assignment.databinding.ItemWeatherBinding
import com.nab.assignment.model.Weather
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Lan Tran on 1/31/21.
 */

class WeatherAdapter(var dataSet: List<Weather>? = emptyList()) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    companion object {
        val formatter: SimpleDateFormat = SimpleDateFormat("EEE, dd MMM yyyy")
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_weather, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binds(dataSet?.get(position))
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet?.size ?: 0

    fun submitList(dataSet: List<Weather>?) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemWeatherBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun binds(item: Weather?) {
            binding.tvInfo.text = if (item == null) {
                ""
            } else {
                "Date: ${formatter.format(Date(item.datetime))}" +
                        "\nAverage temperature: ${item.averageTemperature}°C" +
                        "\nPressure: ${item.pressure}" +
                        "\nHumidity: ${item.humidity}%" +
                        "\nDescription: ${item.weatherDescription}"
            }
        }
    }
}
