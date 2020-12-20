package com.dididi.packrat.presentation.collect

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dididi.packrat.core.domain.Collect
import com.dididi.packrat.framework.BaseViewModel
import com.dididi.packrat.framework.PackRatApp
import com.dididi.packrat.framework.data.Interactors
import com.dididi.uiextlib.ext.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 27/10/2019
 * @describe 收藏页面的viewModel,管理数据 如果需要application的引用 使用AndroidViewModel
 */

class CollectViewModel(application: Application, dependencies: Interactors) :
    BaseViewModel(application, dependencies) {

    //收藏数据
    var collectLiveData = MutableLiveData<Collect>()

    /**
     * 获取收藏集合
     */
    fun getCollects(block: (List<Collect>) -> Unit) = viewModelScope.launch {
        try {
            block(
                withContext(Dispatchers.IO) {
                    dependencies.getCollect().onEach {
                        collectLiveData.postValue(it)
                    }
                }
            )
        } catch (t: Throwable) {
            t.printStackTrace()
            getApplication<PackRatApp>().toast(t.message)
        }
    }

    /**
     * 存储数据
     */
    fun setCollects(collects: List<Collect>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                dependencies.addCollects(collects)
                collects.forEach {
                    collectLiveData.postValue(it)
                }
            }
        }
    }

    fun setCollect(collect: Collect) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                dependencies.addCollect(collect)
                collectLiveData.postValue(collect)
            }
        }
    }
}