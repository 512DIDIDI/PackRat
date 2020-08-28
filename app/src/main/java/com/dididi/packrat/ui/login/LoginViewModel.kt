package com.dididi.packrat.ui.login

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.dididi.packrat.Config
import com.dididi.packrat.PackRatApp
import com.dididi.packrat.data.Repository
import com.dididi.packrat.data.model.login.LoginResponse
import com.dididi.packrat.ui.BaseViewModel


/**
 * @author dididi(yechao)
 * @since 10/12/2019
 * @describe
 */

class LoginViewModel(application: Application) : BaseViewModel(application) {

    /**
     * 登录数据
     */
    var loginLiveData = MutableLiveData<LoginResponse>()
    /**
     * 注册数据
     */
    var registerLiveData = MutableLiveData<LoginResponse>()
    /**
     * 登出数据
     */
    var logOutLiveData = MutableLiveData<LoginResponse>()

    private val repository = Repository.getInstance(application)

    fun login(username: String, password: String) {
        launch {
            loginLiveData.postValue(repository.login(username, password))
        }
    }

    fun register(username: String, password: String, repassword: String) {
        launch {
            registerLiveData.postValue(repository.register(username, password, repassword))
        }
    }

    fun logOut() {
        launch {
            logOutLiveData.postValue(repository.logOut())
            //登出时清空sp中存储的cookie
            PackRatApp.context.getSharedPreferences(Config.COOKIE_FILE, Context.MODE_PRIVATE)
                ?.edit()
                ?.clear()
                ?.apply()
        }
    }

}