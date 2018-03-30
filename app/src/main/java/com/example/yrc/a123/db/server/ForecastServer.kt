package com.example.yrc.a123.db.server

import com.example.yrc.a123.db.ForecastDb
import com.example.yrc.a123.domain.Forecast
import com.example.yrc.a123.domain.ForecastDataSource
import com.example.yrc.a123.domain.ForecastList

/**
 * Created by YRC on 2017/9/26.
 */
class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper()
                     , val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {
    override fun requestDayForecast(id: Long): Forecast?  = throw UnsupportedOperationException()

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result=ForecastByZipCodeRequest(zipCode).execute()
        val converted=dataMapper.convertToDomain(zipCode,result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode,date)
    }
                     }