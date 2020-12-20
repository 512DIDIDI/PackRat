package com.dididi.packrat.core.interactors.login

import com.dididi.packrat.core.data.ILoginDataSource
import com.dididi.packrat.core.domain.LoginResponse

/**
 * author: yechao
 * desc: 交互器 - 注册
 * createTime:2020-12-18
 */
class Register(private val ILoginDataSource: ILoginDataSource) {
    suspend operator fun invoke(userName: String, password: String): LoginResponse {
        return ILoginDataSource.register(userName, password)
    }
}