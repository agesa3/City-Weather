package com.agesadev.weathercell.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agesadev.common.utils.Resource
import com.agesadev.domain.usecases.GetCurrentDayWeatherUsecase
import com.agesadev.weathercell.mappers.toWeatherPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentDayWeather: GetCurrentDayWeatherUsecase,
) : ViewModel() {


    private val _currentDayWeather = MutableStateFlow(HomeWeatherState())
    val currentDayWeather: StateFlow<HomeWeatherState> = _currentDayWeather

    val cityName = "Kisumu"

    init {
       getCurrentDayCityWeather(cityName)
    }

    private fun getCurrentDayCityWeather(cityName: String) {
        viewModelScope.launch {
            getCurrentDayWeather(cityName).onStart {
                _currentDayWeather.value = HomeWeatherState(isLoading = true)
            }.collectLatest { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _currentDayWeather.value =
                            HomeWeatherState(data = resource.data?.toWeatherPresentation())
                    }
                    is Resource.Error -> {
                        _currentDayWeather.value = HomeWeatherState(
                            error = resource.message ?: "Error getting current day weather"
                        )
                    }
                    is Resource.Loading -> {
                        _currentDayWeather.value = HomeWeatherState(isLoading = true)
                    }
                }

            }
        }
    }


}