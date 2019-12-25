package com.dididi.packrat.data.net.interceptor

import android.content.Context
import com.dididi.packrat.Config
import com.dididi.packrat.PackRatApp
import okhttp3.Interceptor
import okhttp3.Response


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 25/12/2019
 * @describe 为请求加上cookie
 */

class AddCookieInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val cookieSet =
            PackRatApp.context.getSharedPreferences(Config.COOKIE_FILE, Context.MODE_PRIVATE)
                .getStringSet(Config.COOKIE_KEY, null)
        cookieSet?.forEach {
            builder.addHeader(Config.COOKIE_NAME, it)
        }
        return chain.proceed(builder.build())
    }
}