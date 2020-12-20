package com.dididi.packrat.core.domain


/**
 * @author dididi(yechao)
 * @since 10/12/2019
 * @describe 登录注册等回调
 */

data class LoginResponse(
    var errorCode: Int,
    var errorMsg: String?,
    var data: Data
) {
    data class Data(
        var id: Int,
        var username: String,
        var password: String,
        var nickname: String,
        var publicname: String,
        var email: String?,
        var icon: String?,
        var type: Int,
        var collectId: List<Int>?
    )
}