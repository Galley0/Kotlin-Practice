package com.example.yrc.a123.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.yrc.a123.App
import com.example.yrc.a123.objects.CityForecastTable
import com.example.yrc.a123.objects.DayForecastTable
import org.jetbrains.anko.db.*

/**
 * Created by YRC on 2017/9/20.
 */
/**
 * 建立更新数据库（Table）
 */
class ForecastDbHelper(ctx: Context =App.instance):ManagedSQLiteOpenHelper(ctx,ForecastDbHelper.DB_NAME,null,ForecastDbHelper.DB_VERSION){
    companion object {
        val DB_NAME="forecast.db"
        val DB_VERSION=1
        val instance:ForecastDbHelper by lazy { ForecastDbHelper() }
    }

    /**db.createTable
     * 第一个参数是表的名称
     * 第二个参数，当时true时候，创建之前会检查这个表是否存在
     * 第三个参数是一个Pair类型的Vararg参数（vararg是说可变长度的参数列表）。
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(CityForecastTable.NAME,true,
                CityForecastTable.ID to INTEGER+ PRIMARY_KEY,
                CityForecastTable.CITY to TEXT,
                CityForecastTable.COUNTY to TEXT)

        db.createTable(DayForecastTable.NAME,true,
                DayForecastTable.ID to INTEGER+ PRIMARY_KEY+ AUTOINCREMENT,
                DayForecastTable.DATE to INTEGER,
                DayForecastTable.DESCRIPTION to TEXT,
                DayForecastTable.HIGH to INTEGER,
                DayForecastTable.LOW to INTEGER,
                DayForecastTable.ICON_URL to TEXT,
                DayForecastTable.CITY_ID to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(CityForecastTable.NAME,true)
        db.dropTable(DayForecastTable.NAME,true)
        onCreate(db)
    }

}