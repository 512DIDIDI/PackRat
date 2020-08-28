package com.dididi.packrat.data.net.api

import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.data.model.login.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


/**
 * @author dididi(yechao)
 * @since 28/08/2020
 * @describe server api
 */

interface PackRatService {
    /**
     * 获取收藏数据
     */
    @GET()
    fun getCollectAsync(): Deferred<MutableList<Collect>>

    /**
     * 登陆接口
     */
    @FormUrlEncoded
    @POST("user/login")
    fun loginAsync(
        @Field("username") username: String,
        @Field("password") password: String
    ): Deferred<LoginResponse>

    /**
     * 注册接口
     */
    @FormUrlEncoded
    @POST("user/register")
    fun registerAsync(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Deferred<LoginResponse>

    /**
     * 登出接口
     */
    @FormUrlEncoded
    @GET("user/logout/json")
    fun quitAsync(): Deferred<LoginResponse>
}