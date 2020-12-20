package com.dididi.packrat.presentation.login

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dididi.packrat.core.domain.LoginResponse
import com.dididi.packrat.framework.BaseViewModel
import com.dididi.packrat.framework.Config
import com.dididi.packrat.framework.PackRatApp
import com.dididi.packrat.framework.data.Interactors
import com.dididi.uiextlib.ext.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * @author dididi(yechao)
 * @since 10/12/2019
 * @describe
 */

class LoginViewModel(application: Application, dependencies: Interactors) :
    BaseViewModel(application, dependencies) {

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


    fun login(username: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loginLiveData.postValue(dependencies.login(username, password))
            }
        }
    }

    fun register(username: String, password: String, repassword: String) {
        viewModelScope.launch {
            if (password != repassword) {
                PackRatApp.context.toast("密码不匹配，请重新输入密码")
            }
            withContext(Dispatchers.IO) {
                registerLiveData.postValue(dependencies.register(username, password))
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                logOutLiveData.postValue(dependencies.logout())
                //登出时清空sp中存储的cookie
                PackRatApp.context.getSharedPreferences(Config.COOKIE_FILE, Context.MODE_PRIVATE)
                    ?.edit()
                    ?.clear()
                    ?.apply()
            }
        }
    }

}