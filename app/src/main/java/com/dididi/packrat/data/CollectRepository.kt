package com.dididi.packrat.data

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

    /**
     * 获取收藏列表数据
     */
    suspend fun getCollects() = try {
        net.fetchCollectList()
    } catch (t: Throwable) {
        t.printStackTrace()
        collectDao.getCollectList()
    }


    /**
     * 设置收藏列表存储入数据库
     */
    suspend fun setCollects(collects: List<Collect>) {
        collects.forEach {
            collectDao.insert(it)
            log(content = it.content)
        }
    }

    /**
     * 插入单个收藏数据
     */
    suspend fun setCollect(collect: Collect) {
        collectDao.insert(collect)
        log(content = collect.content)
    }
}