package com.example.yrc.a123.extensions

import android.content.Context
import android.view.View
import android.widget.TextView

/**
 * Created by YRC on 2017/9/14.
 */
val View.ctx:Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)
