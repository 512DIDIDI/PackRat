package com.dididi.packrat.core.interactors.login

import com.dididi.packrat.core.data.ILoginDataSource
import com.dididi.packrat.core.domain.LoginResponse

/**
 * author: yechao
 * desc: 交互器 - 登出
 * createTime:2020-12-18
 */
class Logout(private val ILoginDataSource: ILoginDataSource) {
    suspend operator fun invoke(): LoginResponse {
        return ILoginDataSource.logout()
    }
}