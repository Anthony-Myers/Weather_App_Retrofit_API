package com.example.weatherappretrofitapi.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherappretrofitapi.R
import com.example.weatherappretrofitapi.databinding.HourlyWeatherItemBinding
import com.example.weatherappretrofitapi.ui.main.data.models.CurHourlyReportDTO
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class HourlyAdapter (
    private val mHourlyList : List<CurHourlyReportDTO>,
    private val mPicasso : Picasso
        ): RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HourlyAdapter.HourlyViewHolder =
        HourlyViewHolder(
            HourlyWeatherItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = mHourlyList.count()

    override fun onBindViewHolder(holder: HourlyAdapter.HourlyViewHolder, position: Int): Unit = with(
        holder
    ) {
        val hourlyReport = mHourlyList[position]
        loadData(hourlyReport)
        mPicasso.load(holder.getIcon().context.getString(R.string.icon_url, hourlyReport.weather[0].iconUrl))
            .into(
                holder.getIcon()
            )
    }

    class HourlyViewHolder(private val binding: HourlyWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun loadData(hourlyReport: CurHourlyReportDTO) = with(binding) {
            val date = Date(hourlyReport.time * 1000)
            val sdf = SimpleDateFormat("HH", Locale.ENGLISH)
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            binding.hourTv.text = sdf.format(date)
            binding.temperatureTv.text = binding.root.context.resources.getString(
                R.string.temperature,
                (hourlyReport.temperature-273.15).format(0).toString()
            )
        }
        private fun Double.format(digits: Int) = "%.${digits}f".format(this)
        fun getIcon(): ImageView = binding.weatherIconIv
    }
}