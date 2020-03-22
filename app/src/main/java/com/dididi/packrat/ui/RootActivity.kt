package com.dididi.packrat.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.dididi.packrat.Config
import com.dididi.packrat.R

/**
 * 根Activity 作为fragment的容器
 */

class RootActivity : AppCompatActivity() {

    lateinit var currentFragment:BaseFragment

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
                .navigate(R.id.action_login_to_home)
        }
    }

    /**
     * 实体返回键的处理
     */
    override fun onBackPressed() {
        when{
            currentFragment.onBackPressed() -> {}
            else -> super.onBackPressed()
        }
    }
}
