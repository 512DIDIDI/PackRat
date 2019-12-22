package com.dididi.packrat.data.net.interceptor

import com.dididi.packrat.utils.log
import okhttp3.Interceptor
import okhttp3.Response


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 22/12/2019
 * @describe 本地保存cookie
 */

class SaveCookieInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        log(content = "intercept:${response.header("Set-Cookie")}")
        return response
    }
}