package com.dididi.packrat.data.net.api

import com.dididi.packrat.data.model.login.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


/**
 * @author dididi(yechao)
 * @since 10/12/2019
 * @describe 登录注册等服务 [LoginResponse]
 */

interface LoginService {
    @FormUrlEncoded
    @POST("user/login")
    fun loginAsync(
        @Field("username") username: String,
        @Field("password") password: String
    ): Deferred<LoginResponse>

    @FormUrlEncoded
    @POST("user/register")
    fun registerAsync(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Deferred<LoginResponse>

    @FormUrlEncoded
    @GET("user/logout/json")
    fun quitAsync(): Deferred<LoginResponse>
}