package com.example.yrc.a123.db

import com.example.yrc.a123.*
import com.example.yrc.a123.domain.Forecast
import com.example.yrc.a123.domain.ForecastDataSource
import com.example.yrc.a123.domain.ForecastList
import com.example.yrc.a123.extensions.*
import com.example.yrc.a123.objects.CityForecastTable
import com.example.yrc.a123.objects.DayForecastTable
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by YRC on 2017/9/21.
 */
/**
 * 从数据库中保存或查询数据
 * parseOpt返回一个可null的对象
 */
class ForecastDb(val forecastDbHelper: ForecastDbHelper=ForecastDbHelper.instance,
                 val dataMapper: DbDataMapper=DbDataMapper()):ForecastDataSource{
    override fun requestDayForecast(id: Long): Forecast? = forecastDbHelper.use {
        val forecast=select(DayForecastTable.NAME).byId(id).parseOpt{ DayForecast(HashMap(it))}
        if (forecast!=null) dataMapper.convertDayToDomain(forecast)else null
    }


    override fun requestForecastByZipCode(zipCode:Long,date:Long)=forecastDbHelper.use {
        val dailyRequest="${DayForecastTable.CITY_ID}={id}"+"AND${DayForecastTable.DATE}>={date}"

        val dailyForecast=select(DayForecastTable.NAME).whereArgs(dailyRequest,"id" to zipCode
                                ,"date" to date).parseList{DayForecast(HashMap(it))}

        val city=select(CityForecastTable.NAME).whereSimple("${CityForecastTable.ID}=?",zipCode.toString())
                .parseOpt { (CityForecast(HashMap(it),dailyForecast))  }


        if(city!=null)dataMapper.convertToDomain(city)else null
    }

    fun saveForecast(forecast:ForecastList)=forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)
        with(dataMapper.convertFromDomain(forecast)){
            insert(CityForecastTable.NAME,*map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME,*it.map.toVarargArray()) }
        }
    }
}