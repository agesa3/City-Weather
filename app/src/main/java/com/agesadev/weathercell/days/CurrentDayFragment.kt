package com.agesadev.weathercell.days

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.agesadev.weathercell.databinding.FragmentCurrentDayBinding
import com.agesadev.weathercell.days.adapters.WeatherRecyclerAdapter
import com.agesadev.weathercell.model.CityWeatherPresentation
import com.agesadev.weathercell.util.Utils.filterForecastBasedOnDate
import com.agesadev.weathercell.util.showProgressBar
import com.agesadev.weathercell.util.showSnackBarWithRetryAction
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@AndroidEntryPoint
class CurrentDayFragment : Fragment() {


    private var _currentDayBinding: FragmentCurrentDayBinding? = null
    private val currentDayBinding get() = _currentDayBinding!!
    private lateinit var weatherRecyclerAdapter: WeatherRecyclerAdapter
    private val detailedWeatherDetailsViewModel: MoreWeatherDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _currentDayBinding = FragmentCurrentDayBinding.inflate(inflater, container, false)
        return currentDayBinding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        getAndObserveWeather()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getAndObserveWeather() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailedWeatherDetailsViewModel.detailsState.collectLatest { state ->
                    when {
                        state.data != null -> {
                            Log.d("Today", "onViewCreated: ${state.data}")
                            weatherRecyclerAdapter.submitList(
                                filterForecastBasedOnDate(
                                    state.data,
                                    0
                                )
                            )
                            currentDayBinding.todayProgessBar.showProgressBar(false)
                        }

                        state.isLoading -> {
                            currentDayBinding.todayProgessBar.showProgressBar(true)
                        }

                        state.error != null -> {
                            showSnackBarWithRetryAction(
                                currentDayBinding.root,
                                state.error
                            ) {
                                getAndObserveWeather()
                            }
                            currentDayBinding.todayProgessBar.showProgressBar(false)
                        }
                    }

                }

            }
        }
    }

    private fun setUpRecyclerView() {
        weatherRecyclerAdapter = WeatherRecyclerAdapter()
        currentDayBinding.todaysRecyclerView.apply {
            adapter = weatherRecyclerAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _currentDayBinding = null
    }


}