package com.dididi.packrat.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dididi.packrat.PackRatApp
import com.dididi.packrat.utils.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 22/12/2019
 * @describe 抽离viewModel的共同方法
 */

abstract class BaseViewModel(application: Application) : AndroidViewModel(application){
    /**
     * 加载变化
     */
    var isLoading = MutableLiveData<Boolean>()
    /**
     * 统一协程处理
     */
    fun launch(block:suspend() -> Unit) = viewModelScope.launch {
        try {
            isLoading.value = true
            withContext(Dispatchers.IO){
                block()
            }
            isLoading.value = false
        }catch (t:Throwable){
            t.printStackTrace()
            getApplication<PackRatApp>().toast(t.message)
            isLoading.value = false
        }
    }
}