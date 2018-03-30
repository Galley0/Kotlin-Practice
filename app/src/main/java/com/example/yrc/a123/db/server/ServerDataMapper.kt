package com.example.yrc.a123.db.server

/**
 * Created by YRC on 2017/9/26.
 */


import com.example.yrc.a123.Forecast
import com.example.yrc.a123.ForecastResult
import com.example.yrc.a123.domain.ForecastList
import java.util.*
import java.util.concurrent.TimeUnit
import com.example.yrc.a123.domain.Forecast as ModelForecast

/**
 * question
 */
class ServerDataMapper {

    fun convertToDomain(zipCode: Long, forecast: ForecastResult) = with(forecast) {
        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        ModelForecast(-1, dt, weather[0].description, temp.max.toInt(), temp.min.toInt(),
                generateIconUrl(weather[0].icon))
    }

    /**
     * 获取图标
     */
    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}