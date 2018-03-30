package com.example.yrc.a123.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by YRC on 2017/9/27.
 */
fun Long.toDateString(dateFormat:Int  = DateFormat.MEDIUM):String{
    val df= DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}