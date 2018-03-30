package com.example.yrc.a123.domain

import com.example.yrc.a123.db.ForecastDb
import com.example.yrc.a123.db.server.ForecastServer
import com.example.yrc.a123.extensions.firstResult

/**
 * Created by YRC on 2017/9/26.
 */
class ForecastProvider(private val sources:List<ForecastDataSource> =ForecastProvider.SOURCES){
    companion object {
        val DAY_IN_MILLIS=1000*60*60*24
        val SOURCES= listOf(ForecastDb(), ForecastServer())

    }
    fun requestByZipCode(zipCode:Long,days:Int):ForecastList = requestToSources {
        val res=it.requestForecastByZipCode(zipCode,todayTimeSpan())
         if (res!=null && res.size >=days ) res else null
    }


    fun requestForecast(id:Long):Forecast= requestToSources { it.requestDayForecast(id) }
    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    private fun <T:Any>requestToSources(f:(ForecastDataSource)->T?):T =sources.firstResult{f(it)}
}
