package com.dididi.packrat.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.dididi.packrat.R
import com.dididi.packrat.utils.showPopupMenu
import com.dididi.packrat.utils.toast
import com.google.android.material.navigation.NavigationView
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
        clickEvent()
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
     * 绑定左侧NavigationView与fragment之间的关系
     */
    private fun setNavBottomBar() {
        val navController = Navigation.findNavController(activity!!, R.id.fragmentMainFragment)
        NavigationUI.setupWithNavController(fragmentMainNavView, navController)
    }

    private fun clickEvent(){
        fragmentMainToolbarDrawer.setOnClickListener {
            fragmentMainDrawerLayout.openDrawer(GravityCompat.START)
        }
        fragmentMainToolbarSearch.setOnClickListener {

        }
        fragmentMainToolbarMore.setOnClickListener {
            showPopupMenu(it,R.menu.menu_toolbar_more)
        }
    }
}