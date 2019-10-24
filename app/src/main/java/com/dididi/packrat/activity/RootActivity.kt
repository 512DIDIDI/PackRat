package com.dididi.packrat.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dididi.packrat.R
import kotlinx.android.synthetic.main.activity_root.*

class RootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        setNavBottomBar()
    }

    /**
     * 绑定bottomBar与fragment之间的关系
     */
    private fun setNavBottomBar() {
        val hostFragment =
            supportFragmentManager.findFragmentById(R.id.activityRootFragment) as NavHostFragment
        NavigationUI.setupWithNavController(activityRootBottomBar, hostFragment.navController)
    }

}
