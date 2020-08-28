package com.dididi.packrat.data

import android.content.Context
import com.dididi.packrat.data.local.PackRatDatabase
import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.data.net.PackRatNetUtil
import com.dididi.packrat.utils.log


/**
 * @author dididi(yechao)
 * @since 28/08/2020
 * @describe 数据依赖层，分发local或者net数据
 */

class Repository private constructor(context:Context) {

    private val net = PackRatNetUtil.getInstance()
    private val collectDao = PackRatDatabase.getInstance(context).collectDao()

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
    }

    /**
     * 登录相关api
     */
    suspend fun login(username: String, password: String) =
        net.fetchLoginResult(username, password)

    suspend fun register(username: String, password: String, repassword: String) =
        net.fetchRegisterResult(username, password, repassword)

    suspend fun logOut() =
        net.fetchQuitResult()

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: Repository(context).apply {
                    instance = this
                }
            }
    }
}