package com.example.yrc.a123.domain.commands

import com.example.yrc.a123.domain.ForecastList
import com.example.yrc.a123.domain.ForecastProvider

/**
 * Created by YRC on 2017/9/26.
 */
class RequestForecastCommand(val zipCode:Long,val forecastProvider: ForecastProvider = ForecastProvider()):
                            Command<ForecastList>{
    companion object {
        val DAYS=7
    }

    override fun execute(): ForecastList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }
}