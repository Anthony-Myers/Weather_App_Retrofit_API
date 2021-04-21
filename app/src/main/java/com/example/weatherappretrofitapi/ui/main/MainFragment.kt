package com.example.weatherappretrofitapi.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherappretrofitapi.R
import com.example.weatherappretrofitapi.databinding.MainFragmentBinding
import com.example.weatherappretrofitapi.ui.main.adapters.DailyAdapter
import com.example.weatherappretrofitapi.ui.main.adapters.HourlyAdapter
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment() {
    private lateinit var mBinding: MainFragmentBinding
    private lateinit var mViewModel: MainViewModel
    private var mWeatherImageUrl: String = "https://s7d2.scene7.com/is/image/TWCNews/1031_nc_sunny_weather_2-1"

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = MainFragmentBinding.inflate(inflater)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val picasso = Picasso.get()


        mViewModel.weatherReport.observe(viewLifecycleOwner, Observer {
            mBinding.weatherTv.text = it.currentWeather.weather[0].weather
            mBinding.temperatureTv.text = context?.resources?.getString(
                R.string.temperature,
                (it.currentWeather.temperature - 273.15).format(0).toString()
            )

            var hourlyAdapter: HourlyAdapter? =
                mViewModel.weatherReport.value?.hourlyWeather?.let { HourlyAdapter(it, picasso) }

            var dailyAdapter: DailyAdapter? =
                mViewModel.weatherReport.value?.dailyWeather?.let { DailyAdapter(it, picasso) }

            mBinding.hourlyRv.apply {
                adapter = hourlyAdapter
            }
            mBinding.dailyRv.apply {
                adapter = dailyAdapter
            }
            val date = Date(it.currentWeather.time * 1000)
            val sdf = SimpleDateFormat("EEEE", Locale.ENGLISH)
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            mBinding.currentDayTv.text = sdf.format(date)

            when(mViewModel.weatherReport.value?.currentWeather?.weather?.get(0)?.weather){
                "Clouds" -> mWeatherImageUrl = "https://images.unsplash.com/photo-1514477917009-389c76a86b68?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXw3NTAyMnwwfDF8c2VhcmNofDN8fHNreXxlbnwwfHx8&ixlib=rb-1.2.1&q=80&w=1080"
                "Rain" -> mWeatherImageUrl =  "https://www.theglassmanwindowwashing.com/static/files/blog/cleanWindowsInRain---GlassManWindowWashing.jpg"
            }

            picasso.load(mWeatherImageUrl)
                .into(
                    mBinding.backgroundIv
                )

            mBinding.loadingContainer.visibility = FrameLayout.INVISIBLE
        })
    }

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)


}