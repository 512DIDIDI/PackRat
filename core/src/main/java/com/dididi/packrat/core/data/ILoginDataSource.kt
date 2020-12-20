package com.dididi.packrat.core.data

import com.dididi.packrat.core.domain.LoginResponse

/**
 * author: yechao
 * desc: 登录接口
 * createTime:2020-12-18
 */
interface ILoginDataSource {
    suspend fun login(userName:String,password:String):LoginResponse

    suspend fun logout():LoginResponse

    suspend fun register(userName: String,password: String):LoginResponse
}