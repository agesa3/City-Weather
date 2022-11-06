package com.agesadev.weathercell.days

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agesadev.common.Constants.DEFAULT_LATITUDE
import com.agesadev.common.Constants.DEFAULT_LONGITUDE
import com.agesadev.common.utils.Resource
import com.agesadev.domain.usecases.GetWeatherForecastByLatLon
import com.agesadev.weathercell.mappers.toCityWeather
import com.agesadev.weathercell.mappers.toWeatherForecastPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreWeatherDetailsViewModel @Inject constructor(
    private val getWeatherForecastByLatLon: GetWeatherForecastByLatLon
) : ViewModel() {

    private val _detailsState = MutableStateFlow(WeatherDetailState())
    val detailsState: StateFlow<WeatherDetailState> = _detailsState


    init {
        getWeatherForecast(0.0, 0.0)
    }

    fun getWeatherForecast(lat: Double, lon: Double) {
        viewModelScope.launch {
            getWeatherForecastByLatLon(lat, lon).onStart {
                _detailsState.value = WeatherDetailState(isLoading = true)

            }.collectLatest { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _detailsState.value =
                            WeatherDetailState(data = resource.data?.list?.map { it.toCityWeather() })
                    }
                    is Resource.Error -> {
                        _detailsState.value = WeatherDetailState(
                            error = resource.message ?: "Error getting weather forecast"
                        )
                    }
                    is Resource.Loading -> {
                        _detailsState.value = WeatherDetailState(isLoading = true)
                    }
                }


            }
        }
    }


}