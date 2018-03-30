package com.example.yrc.a123.extensions

/**
 * Created by YRC on 2017/9/27.
 */
import android.content.Context
import android.support.v4.content.ContextCompat

fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)