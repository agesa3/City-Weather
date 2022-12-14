package com.agesadev.weathercell.days

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agesadev.weathercell.databinding.FragmentDetailedWeatherBinding
import com.agesadev.weathercell.days.adapters.DaysViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailedWeatherFragment : Fragment() {


    private var _detailedWeatherBinding: FragmentDetailedWeatherBinding? = null
    private val detailedWeatherBinding get() = _detailedWeatherBinding!!
    private var daysAdapter: DaysViewPagerAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _detailedWeatherBinding = FragmentDetailedWeatherBinding.inflate(inflater, container, false)
        setUpViewPager()


        return detailedWeatherBinding.root
    }


    private fun setUpViewPager() {
        daysAdapter = DaysViewPagerAdapter(this)
        detailedWeatherBinding.weatherViewPager.adapter = daysAdapter
        TabLayoutMediator(
            detailedWeatherBinding.daysTabLayout,
            detailedWeatherBinding.weatherViewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Today"
                }
                1 -> {
                    tab.text = "Tomorrow"
                }
                2 -> {
                    tab.text = "Later"
                }
            }
        }.attach()
    }

}

