package com.dididi.packrat.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dididi.packrat.Config
import com.dididi.packrat.R
import kotlinx.android.synthetic.main.activity_root.*

/**
 * 根Activity 作为fragment的容器
 */

class RootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        //如果已登录 跳转到首页
        if (!getSharedPreferences(
                Config.COOKIE_FILE,
                Context.MODE_PRIVATE
            ).getStringSet(Config.COOKIE_KEY, null).isNullOrEmpty()
        ) {
            Navigation.findNavController(this, R.id.activityRootFragment)
                .navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }

}
