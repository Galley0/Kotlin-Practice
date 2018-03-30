package com.example.yrc.a123

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import com.example.yrc.a123.domain.commands.RequestDayForecastCommand
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.example.yrc.a123.domain.Forecast
import com.example.yrc.a123.extensions.color
import com.example.yrc.a123.extensions.textColor
import com.example.yrc.a123.extensions.toDateString
import org.jetbrains.anko.*
import java.text.DateFormat


/**
 * Created by YRC on 2017/9/26.
 */

class DetailActivity: AppCompatActivity(),ToolbarManager{
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    companion object {
        val ID="DetailActivity:id"
        val CITY_NAME="DetailActivity:cityName"
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_detail)
        initToolbar()
        toolbarTitle=intent.getStringExtra(CITY_NAME)
        enableHomeAsUp { onBackPressed() }
        title=intent.getStringExtra(CITY_NAME)
        doAsync {
            val result=RequestDayForecastCommand(intent.getLongExtra(ID,-1)).execute()
            uiThread { bindForecast(result) }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun bindForecast(forecast: Forecast)= with(forecast){
        Picasso.with(ctx).load(iconUrl).into(icon)
        supportActionBar?.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
        bindWeather(high to maxTemperature,low to minTemperature)
    }


    private fun bindWeather(vararg views:Pair<Int,TextView>)=views.forEach {
        it.second.text="${it.first.toString()}"
        it.second.textColor = color (when (it.first){
          in -50..0 ->android.R.color.holo_red_dark
          in 0..15 ->android.R.color.holo_orange_dark
            else ->android.R.color.holo_green_dark
        })
    }
}