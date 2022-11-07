package com.agesadev.weathercell.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.agesadev.weathercell.R
import com.agesadev.weathercell.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar

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
//                                tempInDegress.text = state.data.?.let {
//                                    convertKelvinToDegrees(
//                                        it.temp)
//                                }
//                                humidity.text = state.data.main?.humidity.toString()
                                currentTTime.text = getCurrentTime()
                            }
                            homeBinding.viewMoreBtn.setOnClickListener {
//                                val latitude = state.data.coord?.lat
//                                val longitude = state.data.coord?.lon
//                                if (longitude != null) {
//                                    if (latitude != null) {
//                                        moreWeatherDetailsViewModel.getWeatherForecast(latitude, longitude)
//                                    }
//                                }
                                findNavController().navigate(R.id.action_homeFragment_to_detailedWeatherFragment)
                            }

                        }
                        state.error != null -> {
                            homeBinding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                context,
                                "The error is ${state.error}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
            }
        }
    }

    private fun getCurrentTime(): String {
        val current = Calendar.getInstance().time
        val formatter = SimpleDateFormat("hh:mm")
        return formatter.format(current)
    }

    private fun searchCityByName() {
        homeBinding.searchQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                homeWeatherViewModel.getCurrentDayCityWeather(query.toString())
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun convertKelvinToDegrees(kelvin: Double): String {
        return String.format("%.1f", kelvin - 273.15) + "°C"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _homeBinding = null
    }
}
