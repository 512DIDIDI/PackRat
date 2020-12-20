package com.dididi.packrat.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dididi.packrat.framework.data.Interactors

/**
 * author: yechao
 * desc:
 * createTime:2020-12-18
 */
open class BaseViewModel(application: Application,protected val dependencies: Interactors) :
    AndroidViewModel(application) {
    protected val application = getApplication<PackRatApp>()
}