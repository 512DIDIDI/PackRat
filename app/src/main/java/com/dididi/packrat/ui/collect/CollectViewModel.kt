package com.dididi.packrat.ui.collect

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.dididi.packrat.PackRatApp
import com.dididi.packrat.data.CollectRepository
import com.dididi.packrat.data.local.PackRatDatabase
import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.data.net.PackRatNetUtil
import kotlinx.coroutines.launch


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 27/10/2019
 * @describe 收藏页面的viewModel,管理数据 如果需要application的引用 使用AndroidViewModel
 */

class CollectViewModel(application: Application) : AndroidViewModel(application) {

    //是否加载中
    val isLoading = MutableLiveData<Boolean>()
    //数据依赖层
    private val collectRepository: CollectRepository = CollectRepository.getInstance(
        PackRatDatabase.getInstance(
            application,
            viewModelScope
        ).collectDao(), PackRatNetUtil.getInstance()
    )
    //获取本地存储的收藏数据
    var collectLiveData = collectRepository.getLocalCollects()

    /**
     * ui获取数据
     */
    fun getRemoteCollects() {
        launch {
            //从数据依赖层读取数据
            collectLiveData =
                collectRepository.getRemoteCollects() as MutableLiveData<List<Collect>>
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

    /**
     * launch函数处理数据前后耗时操作，加载 清除数据 刷新liveData源数据
     */
    private fun launch(block: suspend () -> Unit) = viewModelScope.launch {
        try {
            isLoading.value = true
            block()
            isLoading.value = false
        } catch (t: Throwable) {
            t.printStackTrace()
            Toast.makeText(PackRatApp.context, t.message, Toast.LENGTH_SHORT).show()
            isLoading.value = false
        }
    }
}