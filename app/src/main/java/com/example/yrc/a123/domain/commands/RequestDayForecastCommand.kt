package com.example.yrc.a123.domain.commands

import com.example.yrc.a123.domain.Forecast
import com.example.yrc.a123.domain.ForecastProvider


/**
 * Created by YRC on 2017/9/26.
 */
class  RequestDayForecastCommand(
        val id:Long,
        val forecastProvider: ForecastProvider= ForecastProvider()
):Command<Forecast>{
    override fun execute()=forecastProvider.requestForecast(id)
}