package com.agesadev.weathercell.days.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agesadev.common.Constants
import com.agesadev.common.Constants.IMAGE_FORMAT
import com.agesadev.common.Constants.IMAGE_URL
import com.agesadev.weathercell.R
import com.agesadev.weathercell.databinding.SingleWeatherCardBinding
import com.agesadev.weathercell.model.CityWeatherPresentation
import com.agesadev.weathercell.util.Utils.capitalizeTheFirstLetterOfTheWord
import com.agesadev.weathercell.util.Utils.convertKelvinToDegrees
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

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
                weatherDescriptionCard.text =
                    capitalizeTheFirstLetterOfTheWord(cityWeatherPresentation.weather[0].main)
                Glide.with(itemView.context)
                    .load(
                        IMAGE_URL + cityWeatherPresentation.weather[0].icon + IMAGE_FORMAT
                    )
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.default_weather)
                    .into(singleWeatherIcon)
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