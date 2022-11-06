package com.agesadev.weathercell.days

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.agesadev.weathercell.R
import com.agesadev.weathercell.databinding.FragmentDetailedWeatherBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailedWeatherFragment : Fragment() {


    private var _detailedWeatherBinding: FragmentDetailedWeatherBinding? = null
    private val detailedWeatherBinding get() = _detailedWeatherBinding!!

    private val detailedWeatherViewModel: MoreWeatherDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _detailedWeatherBinding = FragmentDetailedWeatherBinding.inflate(inflater, container, false)
        return detailedWeatherBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailedWeatherViewModel.detailsState.collectLatest { state ->
                    when {
                        state.isLoading -> {
                            Toast.makeText(context, "Were Here Loading", Toast.LENGTH_SHORT).show()
                        }
                        state.error != null -> {
                            Toast.makeText(
                                context,
                                "The error is ${state.error}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        state.data != null -> {
                            Toast.makeText(
                                context,
                                "The data is ${state.data}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
            }
        }
    }
}

