package com.dididi.packrat

import android.app.Application
import com.dididi.packrat.utils.LogUtil
import com.tencent.smtt.sdk.QbSdk


/**
 * @author dididi(yechao)
 * @since 09/10/2019
 * @describe
 */

class PackRatApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //非wifi环境下也能下载x5内核
        QbSdk.setDownloadWithoutWifi(true)
        QbSdk.initX5Environment(this, object : QbSdk.PreInitCallback {
            override fun onCoreInitFinished() {
                LogUtil.d("onCoreInitFinished")
            }

            override fun onViewInitFinished(p0: Boolean) {

            }
        })
    }
}