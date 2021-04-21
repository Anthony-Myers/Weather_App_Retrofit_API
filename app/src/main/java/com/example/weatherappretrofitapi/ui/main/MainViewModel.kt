package com.example.weatherappretrofitapi.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherappretrofitapi.ui.main.data.models.ResultsDTO
import com.example.weatherappretrofitapi.ui.main.data.repos.WeatherRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {
    private val disposable = CompositeDisposable()
    private val weatherRepo: WeatherRepo by lazy{
        WeatherRepo()
    }

    private var _weatherReport = MutableLiveData<ResultsDTO>()
    val weatherReport get() = _weatherReport

    init{
        getWeatherReport()
    }

    private fun getWeatherReport() =
        disposable.add(
            weatherRepo.getWeather().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::doOnSuccess, this::doOnError)
        )

    private fun doOnSuccess(newWeatherReport: ResultsDTO){
        _weatherReport.value = newWeatherReport
    }

    private fun doOnError(e : Throwable){
        Log.d(TAG, e.message.toString())
    }

    companion object{
        private val TAG = MainViewModel::class.simpleName
    }
}