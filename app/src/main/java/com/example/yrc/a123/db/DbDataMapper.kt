package com.example.yrc.a123.db

import android.support.annotation.NonNull
import com.example.yrc.a123.domain.Forecast
import com.example.yrc.a123.domain.ForecastList
import org.jetbrains.annotations.Nullable


/**
 * Created by YRC on 2017/9/21.
 */

class DbDataMapper {

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

     fun convertDayToDomain(dayForecast: DayForecast) = with(
            dayForecast) {
        Forecast(_id,date, description, high, low, iconUrl)
    }

    /**
     * 从Domain model转换
     */
    fun convertFromDomain(forecast: ForecastList)= with(forecast){
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id,city,country,daily)
    }

    fun convertDayFromDomain(cityId:Long,forecast: Forecast)= with(forecast){
                DayForecast(date,description,high,low,iconUrl,cityId)
            }
}
