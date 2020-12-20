package com.dididi.packrat.framework.data.net

import com.dididi.packrat.framework.data.net.api.PackRatService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    private val packRatService = ServiceCreator.create(PackRatService::class.java)

    /**
     * 从服务器获取收藏列表
     */
    suspend fun fetchCollectList() = packRatService.getCollectAsync().await()

    /**
     * 获取登录结果
     */
    suspend fun fetchLoginResult(username: String, password: String) =
        packRatService.loginAsync(username, password).await()

    /**
     * 获取注册结果
     */
    suspend fun fetchRegisterResult(username: String,password: String,repassword:String) =
        packRatService.registerAsync(username, password, repassword).await()

    /**
     * 登出结果
     */
    suspend fun fetchQuitResult() = packRatService.quitAsync().await()

    /**
     * 此方法用于retrofit使用 [Call] 的 [Callback] 回调与协程 [await] 的回调相连
     * 不过 retrofit 后续提供了[CoroutineCallAdapterFactory]，可返回[Deferred]作为回调
     * @Deprecated 引入[com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter]包可以使用Deferred作为回调
     */
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

    companion object {
        @Volatile
        private var instance: PackRatNetUtil? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: PackRatNetUtil().apply {
                instance = this
            }
        }
    }
}