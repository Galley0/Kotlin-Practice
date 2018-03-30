package com.example.yrc.a123

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.yrc.a123.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by YRC on 2017/10/6.
 */
class SettingActivity:AppCompatActivity(){
    companion object {
        val ZIP_CODE="zipCode"
        val DEFAULT_ZIP=94043L
    }
    private var zipCode:Long by DelegatesExt.preference(this, ZIP_CODE, DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        cityCode.setText(zipCode.toString())
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem)=when(item.itemId){
        android.R.id.home ->{onBackPressed();true}
        else -> false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        zipCode=cityCode.text.toString().toLong()
    }
}