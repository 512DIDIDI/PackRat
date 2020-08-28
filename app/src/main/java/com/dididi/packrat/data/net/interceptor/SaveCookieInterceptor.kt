package com.dididi.packrat.data.net.interceptor

import android.content.Context
import com.dididi.packrat.Config
import com.dididi.packrat.PackRatApp
import okhttp3.Interceptor
import okhttp3.Response


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 22/12/2019
 * @describe 本地保存cookie
 */

class SaveCookieInterceptor : Interceptor {
    companion object{
        const val LOGIN_URL = "user/login"
        const val REGISTER_URL = "user/register"
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val requestUrl = request.url().toString()
        //如果是登录或注册请求，保存cookie
        if (requestUrl.contains(LOGIN_URL) || requestUrl.contains(REGISTER_URL)){
            //获取请求返回的cookie
            val cookies = response.headers(Config.SET_COOKIE_KEY)
            if (cookies.isNotEmpty()) {
                val cookieSet = HashSet<String>()
                cookies.forEach {
                    cookieSet.add(it)
                }
                //存到sp文件中
                val sp =
                    PackRatApp.context.getSharedPreferences(Config.COOKIE_FILE, Context.MODE_PRIVATE)
                val editor = sp.edit()
                editor.putStringSet(Config.COOKIE_KEY, cookieSet)
                editor.apply()
            }
        }
        return response
    }
}