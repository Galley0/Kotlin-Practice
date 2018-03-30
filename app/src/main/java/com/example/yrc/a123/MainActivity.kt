package com.example.yrc.a123

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.example.yrc.a123.domain.commands.Command
import com.example.yrc.a123.domain.ForecastList
import com.example.yrc.a123.domain.commands.RequestForecastCommand
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() ,ToolbarManager{

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        forecastList.layoutManager=LinearLayoutManager(this)
        attachToScroll(forecastList)


        }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }
    private fun loadForecast()=doAsync {
        val result=RequestForecastCommand(94043).execute()
        uiThread {
            val adapter=ForecastListAdapter(result){
                startActivity<DetailActivity>(DetailActivity.ID to it.id ,DetailActivity.CITY_NAME
                        to result.city)
            }
            forecastList.adapter=adapter
            toolbarTitle="${result.city}(${result.country})"
        }
    }



    }


/**
 * 创建一个必须要的map
 * conf = Configuration(mapOf(
    "width" to 1080,
    "height" to 720,
    "dp" to 240,
    "deviceName" to "mydevice"
  ))
 */
//private class NotNullSingleValueVar<T>():ReadWriteProperty<Any?,T>{
//    private var value:T?=null
//    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
//       return value?:throw IllegalStateException("${desc.name}"+"not initialized")
//    }
//
//    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
//
//    }
//
//
//}
/**
 * 使用ManagedSqliteOpenHelper
 */
//forecastDbHelper.use{
//
//}
/**
 * 在lambda中，直接使用SqliteDatabase中的函数
 */
//public fun <T>use(f:SQLiteDatabase.()->T):T{
//    try {
//        return openDatabase().f()
//    }finally {
//        closeDatabase()
//    }
//}

//val result=forecastDbHelper.use{
//    val queriedObject=....
//            queriedObject
//}


