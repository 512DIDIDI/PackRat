package com.dididi.packrat.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dididi.packrat.data.local.CollectDao
import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.data.net.PackRatNetUtil
import com.dididi.packrat.utils.log


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 28/10/2019
 * @describe 收藏界面 数据依赖库 vm层通过此获取model层数据
 */

class CollectRepository private constructor(
    private var collectDao: CollectDao,
    private var net: PackRatNetUtil
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

    fun getLocalCollects() = collectDao.getCollectList()

    /**
     * 获取收藏列表LiveData
     */
    suspend fun getRemoteCollects(): LiveData<List<Collect>> {
        val list = net.fetchCollectList()
        return MutableLiveData<List<Collect>>().apply {
            value = list
            setCollects(list)
        }
    }


    /**
     * 设置收藏列表存储入数据库
     */
    suspend fun setCollects(collects: List<Collect>) {
        collects.forEach {
            collectDao.insert(it)
            log(it.content)
        }
    }
}