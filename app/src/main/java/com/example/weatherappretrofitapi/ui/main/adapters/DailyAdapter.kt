package com.example.weatherappretrofitapi.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappretrofitapi.R
import com.example.weatherappretrofitapi.databinding.DailyWeatherItemBinding
import com.example.weatherappretrofitapi.ui.main.data.models.DailyReportDTO
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class DailyAdapter(
    private val mDailyList: List<DailyReportDTO>,
    private val mPicasso: Picasso
) : RecyclerView.Adapter<DailyAdapter.DailyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DailyAdapter.DailyViewHolder =
        DailyViewHolder(
            DailyWeatherItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = mDailyList.count()

    override fun onBindViewHolder(holder: DailyAdapter.DailyViewHolder, position: Int): Unit = with(
        holder
    ) {
        val dailyReport = mDailyList[position]
        loadData(dailyReport)
        mPicasso.load(holder.getIcon().context.getString(R.string.icon_url, dailyReport.weather[0].iconUrl))
            .into(
                holder.getIcon()
            )
    }

    class DailyViewHolder(private val binding: DailyWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun loadData(dailyReport: DailyReportDTO) = with(binding) {
            val date = Date(dailyReport.day * 1000)
            val sdf = SimpleDateFormat("EEEE", Locale.ENGLISH)
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            binding.dayTv.text = sdf.format(date)
            binding.temperatureTv.text = binding.root.context.resources.getString(
                R.string.temperature,
                (dailyReport.temperature.dayTemp-273.15).format(0).toString()
            )
        }
        private fun Double.format(digits: Int) = "%.${digits}f".format(this)
        fun getIcon(): ImageView = binding.weatherIconIv
    }
}