package com.agesadev.weathercell.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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

    private val homeWeatherViewModel: WeatherViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, "Creaeted here", Toast.LENGTH_SHORT).show()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeWeatherViewModel.currentDayWeather.collectLatest { state ->
                    when {
                        state.isLoading -> {
                            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                            homeBinding.progressBar.visibility = View.VISIBLE
                        }
                        state.data != null -> {

                            homeBinding.apply {
                                progressBar.visibility = View.GONE
                                cityName.text = state.data.name
                                tempInDegress.text = convertKelvinToDegrees(state.data.main.temp)
                                humidity.text = state.data.main.humidity.toString()
                                currentTTime.text = getCurrentTime()
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

    private fun convertKelvinToDegrees(kelvin: Double): String {
        return String.format("%.1f", kelvin - 273.15)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _homeBinding = null
    }
}
