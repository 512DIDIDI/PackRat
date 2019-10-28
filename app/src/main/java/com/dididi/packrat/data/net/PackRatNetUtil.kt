package com.dididi.packrat.data.net

import com.dididi.packrat.data.net.api.CollectService
import com.dididi.packrat.data.net.api.MeService
import com.dididi.packrat.data.net.api.TodoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 28/10/2019
 * @describe 服务器工具类 暴露给vm层调用的方法(从服务器获取接口数据)
 */

class PackRatNetUtil private constructor() {
    companion object {
        @Volatile
        private var instance: PackRatNetUtil? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: PackRatNetUtil().apply {
                instance = this
            }
        }
    }

    private val collectService = ServiceCreator.create(CollectService::class.java)

    private val todoService = ServiceCreator.create(TodoService::class.java)

    private val meService = ServiceCreator.create(MeService::class.java)

    /**
     * 从服务器获取收藏列表
     */
    suspend fun fetchCollectList() = collectService.getCollect().await()

    private suspend fun <T> Call<T>.await(): T = suspendCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                if (body != null) {
                    continuation.resume(body)
                } else {
                    continuation.resumeWithException(NullPointerException("response body is null"))
                }
            }
        })
    }
}