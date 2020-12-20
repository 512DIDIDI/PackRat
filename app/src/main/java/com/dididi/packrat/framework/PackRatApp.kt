package com.dididi.packrat.framework

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Entity


/**
 * @author dididi(yechao)
 * @since 09/10/2019
 * @describe
 */

class PackRatApp : Application() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context:Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}