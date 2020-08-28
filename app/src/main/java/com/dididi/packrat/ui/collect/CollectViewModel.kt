package com.dididi.packrat.ui.collect

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dididi.packrat.PackRatApp
import com.dididi.packrat.data.Repository
import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.ui.BaseViewModel
import com.dididi.packrat.utils.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 27/10/2019
 * @describe 收藏页面的viewModel,管理数据 如果需要application的引用 使用AndroidViewModel
 */

class CollectViewModel(application: Application) : BaseViewModel(application) {

    //数据依赖层
    private val repo: Repository = Repository.getInstance(application)

    //收藏数据
    var collectLiveData = MutableLiveData<Collect>()

    /**
     * 获取收藏集合
     */
    fun getCollects(block: (List<Collect>) -> Unit) = viewModelScope.launch {
        try {
            isLoading.value = true
            block(
                withContext(Dispatchers.IO) {
                    repo.getCollects()
                }
            )
            isLoading.value = false
        }catch (t:Throwable){
            t.printStackTrace()
            getApplication<PackRatApp>().toast(t.message)
            isLoading.value = false
        }
    }

    /**
     * 存储数据
     */
    fun setCollects(collects: List<Collect>) {
        launch {
            repo.setCollects(collects)
            collects.forEach {
                collectLiveData.postValue(it)
            }
        }
    }

    fun setCollect(collect: Collect) {
        launch {
            repo.setCollect(collect)
            collectLiveData.postValue(collect)
        }
    }
}