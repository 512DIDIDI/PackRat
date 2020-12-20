package com.dididi.packrat.framework

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dididi.packrat.framework.data.Interactors

/**
 * author: yechao
 * desc: [BaseViewModel] 构造工厂
 * createTime:2020-12-18
 */
object PackRatViewModelFactory : ViewModelProvider.Factory {

    private var application: Application? = null
    private var dependencies: Interactors? = null

    /**
     * 依赖注入 - 注入交互器
     */
    fun inject(application: Application, dependencies: Interactors) {
        PackRatViewModelFactory.application = application
        PackRatViewModelFactory.dependencies = dependencies
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (BaseViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(Application::class.java, Interactors::class.java)
                .newInstance(application, dependencies)
        } else {
            throw IllegalStateException("ViewModel must extends com.dididi.packrat.framework.BaseViewModel")
        }
    }
}