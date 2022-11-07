package com.agesadev.weathercell.days

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.agesadev.weathercell.databinding.FragmentTomorrowBinding
import com.agesadev.weathercell.days.adapters.WeatherRecyclerAdapter
import com.agesadev.weathercell.home.WeatherViewModel
import com.agesadev.weathercell.util.Utils.filterForecastBasedOnDate
import com.agesadev.weathercell.util.showProgressBar
import com.agesadev.weathercell.util.showSnackBarWithRetryAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class TomorrowFragment : Fragment() {

    private var _tomorrowBinding: FragmentTomorrowBinding? = null
    private val tomorrowBinding get() = _tomorrowBinding!!
    private lateinit var weatherRecyclerAdapter: WeatherRecyclerAdapter
    private val homeWeatherViewModel: WeatherViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _tomorrowBinding = FragmentTomorrowBinding.inflate(layoutInflater, container, false)
        return tomorrowBinding.root
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
                homeWeatherViewModel.currentDayWeather.collectLatest { state ->
                    when {
                        state.data != null -> {
                            Log.d("Tomorrow", "onViewCreated: ${state.data}")
                            weatherRecyclerAdapter.submitList(
                                filterForecastBasedOnDate(
                                    state.data.list,
                                    1
                                )
                            )
                            tomorrowBinding.tomorrowProgressBar.showProgressBar(false)
                        }

                        state.isLoading -> {
                            tomorrowBinding.tomorrowProgressBar.showProgressBar(true)

                        }

                        state.error != null -> {
                            tomorrowBinding.tomorrowProgressBar.showProgressBar(false)
                            showSnackBarWithRetryAction(
                                tomorrowBinding.tomorrowProgressBar,
                                state.error
                            ) {
                                getAndObserveWeather()
                            }
                        }
                    }

                }

            }
        }
    }

    private fun setUpRecyclerView() {
        weatherRecyclerAdapter = WeatherRecyclerAdapter()
        tomorrowBinding.tomorrowRecyclerView.apply {
            adapter = weatherRecyclerAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _tomorrowBinding = null
    }

}