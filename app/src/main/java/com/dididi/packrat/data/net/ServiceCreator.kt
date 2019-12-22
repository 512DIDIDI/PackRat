package com.dididi.packrat.data.net

import com.dididi.packrat.Config
import com.dididi.packrat.data.net.interceptor.SaveCookieInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 28/10/2019
 * @describe 封装retrofit
 */

object ServiceCreator {
    private val httpClient = OkHttpClient
        .Builder()
        //保存cookie
        .addInterceptor(SaveCookieInterceptor())
        .build()

    private val builder = Retrofit.Builder()
        .baseUrl(Config.BASE_URL)
        .client(httpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        //配合kotlin协程使用
        .addCallAdapterFactory(CoroutineCallAdapterFactory())

    private val retrofit = builder.build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}