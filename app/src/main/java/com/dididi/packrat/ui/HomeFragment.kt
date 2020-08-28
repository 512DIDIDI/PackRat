package com.dididi.packrat.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.dididi.packrat.R
import com.dididi.packrat.utils.showPopupMenu
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 21/12/2019
 * @describe 主页fragment，带有bottomBar的fragment
 */

class HomeFragment : BaseFragment() {

    override fun setLayout() = R.layout.fragment_home

    override fun bindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun doBusiness() {
        //navigation+DrawerLayout必须搭配supportActionBar使用
        mActivity.setSupportActionBar(fragmentMainToolbar)
        setNavBottomBar()
        setImmersionBar()
        clickEvent()
    }

    override fun onBackPressed(): Boolean {
        //执行全局导航的fragment返回栈
        getMainNav(requireActivity()).popBackStack()
        return true
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
        NavigationUI.setupWithNavController(fragmentMainNavView, getHomeNav(requireActivity()))
    }

    private fun clickEvent() {
        fragmentMainToolbarDrawer.setOnClickListener {
            fragmentMainDrawerLayout.openDrawer(GravityCompat.START)
        }
        fragmentMainToolbarSearch.setOnClickListener {

        }
        fragmentMainToolbarMore.setOnClickListener {
            showPopupMenu(it, R.menu.menu_toolbar_more)
        }
    }
}