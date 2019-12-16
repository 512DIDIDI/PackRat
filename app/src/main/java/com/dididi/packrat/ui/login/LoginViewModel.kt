package com.dididi.packrat.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData


/**
 * @author dididi(yechao)
 * @since 10/12/2019
 * @describe
 */

class LoginViewModel(application: Application) :AndroidViewModel(application){
    var isLoading = MutableLiveData<Boolean>()
}