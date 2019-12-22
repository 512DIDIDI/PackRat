package com.dididi.packrat.data

import com.dididi.packrat.data.net.PackRatNetUtil


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 22/12/2019
 * @describe
 */
class LoginRepository private constructor(
    private val net: PackRatNetUtil
) {
    companion object {
        @Volatile
        private var instance: LoginRepository? = null

        fun getInstance(net: PackRatNetUtil) =
            instance ?: synchronized(this) {
                instance ?: LoginRepository(net).apply {
                    instance = this
                }
            }
    }

    suspend fun login(username: String, password: String) =
        net.fetchLoginResult(username, password)

    suspend fun register(username: String, password: String, repassword: String) =
        net.fetchRegisterResult(username, password, repassword)

    suspend fun logOut() =
        net.fetchQuitResult()
}