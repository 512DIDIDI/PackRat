package com.dididi.packrat.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.dididi.packrat.R
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 21/12/2019
 * @describe 主页fragment，带有bottomBar的fragment
 */

class MainFragment : BaseFragment() {
    override fun setLayout() = R.layout.fragment_main

    override fun bindView(savedInstanceState: Bundle?, rootView: View) {
        //navigation+DrawerLayout必须搭配supportActionBar使用
        mActivity.setSupportActionBar(fragmentMainToolbar)
        setNavBottomBar()
        setImmersionBar()
    }

    override fun bindChildView(savedInstanceState: Bundle?, rootView: View) {

    }

    /**
     * 设置状态栏导航栏状态
     */
    private fun setImmersionBar() {
        immersionBar {
            reset()
            barColor(R.color.backgroundColorWhite)
            statusBarDarkFont(true)
            navigationBarDarkIcon(true)
            fitsSystemWindows(true)
        }
    }

    /**
     * 绑定bottomBar与fragment之间的关系
     */
    private fun setNavBottomBar() {
        val navController = Navigation.findNavController(mActivity,R.id.fragmentMainFragment)
        NavigationUI.setupWithNavController(fragmentMainNavView, navController)
        val appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        NavigationUI.setupActionBarWithNavController(mActivity, navController, appBarConfiguration)
    }


}