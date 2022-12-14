package com.agesadev.weathercell.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.agesadev.common.Constants.IMAGE_FORMAT
import com.agesadev.common.Constants.IMAGE_URL
import com.agesadev.weathercell.R
import com.agesadev.weathercell.databinding.FragmentHomeBinding
import com.agesadev.weathercell.util.Utils.capitalizeTheFirstLetterOfTheWord
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _homeBinding: FragmentHomeBinding? = null
    private val homeBinding get() = _homeBinding!!
    private val homeWeatherViewModel: WeatherViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        homeBinding.viewMoreBtn.setOnClickListener {
            findNavController().navigate(R.id.detailedWeatherFragment)
        }
        return homeBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchCityByName()
        getAndObserverWeather()
    }

    private fun getAndObserverWeather() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeWeatherViewModel.currentDayWeather.collectLatest { state ->
                    when {
                        state.isLoading -> {
                            homeBinding.progressBar.visibility = View.VISIBLE
                        }
                        state.data != null -> {
                            homeBinding.apply {
                                progressBar.visibility = View.GONE
                                cityName.text = state.data.city.name
                                sunsetTime.text = getSunsetOrSunriseTime(state.data.city.sunset)
                                sunriseTime.text = getSunsetOrSunriseTime(state.data.city.sunrise)
                                countyCode.text = state.data.city.country
                                weatherDescription.text = capitalizeTheFirstLetterOfTheWord(state.data.list[0].weather[0].main)
                                getIconUrlAndLoadImageUsingGlide(state.data.list[0].weather[0].icon)
                            }
                        }
                        state.error != null -> {
                            homeBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
        }
    }

    private fun getSunsetOrSunriseTime(sunset: Int): String {
        val date = Date(sunset.toLong() * 1000)
        val format = SimpleDateFormat("HH:mm")
        return format.format(date)

    }

    private fun getIconUrlAndLoadImageUsingGlide(icon: String) {
        val iconUrl = IMAGE_URL + icon + IMAGE_FORMAT
        Glide.with(this)
            .load(iconUrl)
            .error(R.drawable.default_weather)
            .transition(withCrossFade())
            .into(homeBinding.weatherIcon)
    }




    private fun searchCityByName() {
        homeBinding.searchQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                homeWeatherViewModel.getCurrentDayCityWeather(query.toString().trim())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _homeBinding = null
    }
}
