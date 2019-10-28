package com.dididi.packrat.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dididi.packrat.data.local.CollectDao
import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.data.net.PackRatNetUtil


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 28/10/2019
 * @describe 收藏界面 数据依赖库 vm层通过此获取model层数据
 */

class CollectRepository private constructor(
    private val collectDao: CollectDao,
    private val net: PackRatNetUtil
) {
    companion object {
        @Volatile
        private var instance: CollectRepository? = null

        fun getInstance(collectDao: CollectDao, net: PackRatNetUtil) =
            instance ?: synchronized(this) {
                instance ?: CollectRepository(collectDao, net).apply {
                    instance = this
                }
            }
    }

    /**
     * 获取收藏列表LiveData
     */
    suspend fun getCollects() =
        if (collectDao.getCollectList().value!!.isEmpty()) {
            val list = net.fetchCollectList()
            MutableLiveData<List<Collect>>().apply {
                value = list
                list.forEach {
                    collectDao.insert(it)
                }
            }
        } else {
            collectDao.getCollectList()
        }
}