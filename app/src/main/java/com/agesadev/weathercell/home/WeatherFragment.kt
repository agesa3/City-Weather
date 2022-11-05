package com.agesadev.weathercell.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.agesadev.weathercell.databinding.FragmentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherFragment : Fragment() {


    private val homeWeatherViewModel: WeatherViewModel by viewModels()
    private var _weatherFragmentBinding: FragmentWeatherBinding? = null
    private val weatherFragmentBinding = _weatherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _weatherFragmentBinding = FragmentWeatherBinding.inflate(inflater, container, false)
        val view = weatherFragmentBinding?.root
        Toast.makeText(context, "Here", Toast.LENGTH_SHORT).show()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeWeatherViewModel.currentDayWeather.collectLatest { state ->
                    when {
                        state.isLoading -> {
                            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                        }
                        state.data != null -> {
                            Toast.makeText(context, "The data is ${state.data}", Toast.LENGTH_SHORT)
                                .show()
                        }
                        state.error != null -> {
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

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _weatherFragmentBinding = null
//    }
}