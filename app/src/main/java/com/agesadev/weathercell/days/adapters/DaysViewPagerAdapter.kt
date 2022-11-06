package com.agesadev.weathercell.days.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.agesadev.weathercell.days.CurrentDayFragment
import com.agesadev.weathercell.days.LaterFragment
import com.agesadev.weathercell.days.TomorrowFragment
import com.agesadev.weathercell.model.WeatherData

class DaysViewPagerAdapter(val fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CurrentDayFragment()
            1 -> TomorrowFragment()
            2 -> LaterFragment()
            else -> CurrentDayFragment()
        }
    }

}