package com.dididi.packrat

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.dididi.packrat.utils.log


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