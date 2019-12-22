package com.dididi.packrat

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.dididi.packrat.utils.log
import com.tencent.smtt.sdk.QbSdk


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
        //非wifi环境下也能下载x5内核
        QbSdk.setDownloadWithoutWifi(true)
        QbSdk.initX5Environment(this, object : QbSdk.PreInitCallback {
            override fun onCoreInitFinished() {
                log(content = "onCoreInitFinished")
            }

            override fun onViewInitFinished(p0: Boolean) {

            }
        })
        context = this
    }
}