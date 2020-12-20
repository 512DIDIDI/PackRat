package com.dididi.packrat.core.interactors.login

import com.dididi.packrat.core.data.ILoginDataSource
import com.dididi.packrat.core.domain.LoginResponse

/**
 * author: yechao
 * desc: 交互器 - 登录
 * createTime:2020-12-18
 */
class Login(private val ILoginDataSource: ILoginDataSource) {
    suspend operator fun invoke(userName: String, password: String): LoginResponse {
        return ILoginDataSource.login(userName, password)
    }
}