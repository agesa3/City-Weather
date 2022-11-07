package com.agesadev.weathercell.days.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agesadev.weathercell.databinding.SingleWeatherCardBinding
import com.agesadev.weathercell.model.CityWeatherPresentation
import com.agesadev.weathercell.util.Utils.convertKelvinToDegrees

class WeatherRecyclerAdapter :
    ListAdapter<CityWeatherPresentation, WeatherRecyclerAdapter.WeatherViewHolder>(
        weatherDiffUtil
    ) {


    inner class WeatherViewHolder(private val weatherCardItemBinding: SingleWeatherCardBinding) :
        RecyclerView.ViewHolder(weatherCardItemBinding.root) {

        fun bind(cityWeatherPresentation: CityWeatherPresentation) {
            weatherCardItemBinding.apply {
                windSpeed.text = cityWeatherPresentation.wind.speed.toString()
                humidity.text = cityWeatherPresentation.main.humidity.toString()
                dateTimeText.text = cityWeatherPresentation.dt_txt
                pressure.text = cityWeatherPresentation.main.pressure.toString()
                tempInDegrees.text = convertKelvinToDegrees(cityWeatherPresentation.main.temp)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            SingleWeatherCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherItem = getItem(position)
        holder.bind(weatherItem)
    }


}

object weatherDiffUtil : DiffUtil.ItemCallback<CityWeatherPresentation>() {
    override fun areItemsTheSame(
        oldItem: CityWeatherPresentation,
        newItem: CityWeatherPresentation
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: CityWeatherPresentation,
        newItem: CityWeatherPresentation
    ): Boolean {
        return oldItem == newItem
    }

}