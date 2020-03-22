package com.dididi.packrat.ui

import androidx.navigation.Navigation
import com.dididi.packrat.R


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 22/03/2020
 * @describe D:\androidwork\PackRat\app\src\main\res\navigation\navigation_home.xml
 *           首页三个tab的导航fragment 默认拦截返回键
 */
abstract class BaseHomeNavFragment:BaseFragment() {

    /**
     * 全局的fragment导航，不是首页三个tab的导航
     * 实际上用的是PackRat\app\src\main\res\navigation\navigation_main.xml
     */
    protected val mainNavController by lazy {
        Navigation.findNavController(activity!!, R.id.activityRootFragment)
    }

    /**
     * 首页三个tab的导航
     */
    protected val homeNavController by lazy {
        Navigation.findNavController(activity!!, R.id.fragmentMainFragment)
    }

    override fun onBackPressed(): Boolean {
        homeNavController.popBackStack()
        return true
    }
}