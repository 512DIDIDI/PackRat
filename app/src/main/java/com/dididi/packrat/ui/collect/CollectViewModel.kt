package com.dididi.packrat.ui.collect

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dididi.packrat.data.CollectRepository
import com.dididi.packrat.data.local.PackRatDatabase
import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.data.net.PackRatNetUtil
import com.dididi.packrat.ui.BaseViewModel


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 27/10/2019
 * @describe 收藏页面的viewModel,管理数据 如果需要application的引用 使用AndroidViewModel
 */

class CollectViewModel(application: Application) : BaseViewModel(application) {

    //数据依赖层
    private val collectRepository: CollectRepository = CollectRepository.getInstance(
        PackRatDatabase.getInstance(
            application,
            viewModelScope
        ).collectDao(), PackRatNetUtil.getInstance()
    )
    //收藏数据
    var collectLiveData = MutableLiveData<List<Collect>>()

    /**
     * ui获取数据
     */
    fun getCollects() {
        launch {
            //从数据依赖层读取数据
            collectLiveData.postValue(collectRepository.getCollects())
        }
    }

    /**
     * 存储数据
     */
    fun setCollects(collects: List<Collect>) {
        launch {
            collectRepository.setCollects(collects)
        }
    }
}