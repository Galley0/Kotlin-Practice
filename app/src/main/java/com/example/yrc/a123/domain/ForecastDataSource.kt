package com.example.yrc.a123.domain

/**
 * Created by YRC on 2017/9/26.
 */
interface ForecastDataSource{
    fun requestForecastByZipCode(zipCode:Long, date:Long):ForecastList?

    fun requestDayForecast(id:Long):Forecast?
}