package com.dididi.packrat.ui

import androidx.navigation.Navigation
import com.dididi.packrat.R


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 22/03/2020
 * @describe PackRat\app\src\main\res\navigation\navigation_main.xml 为导航的fragment
 *          需要拦截其返回键，否则会直接关闭整个app
 */
abstract class BaseMainNavFragment : BaseFragment() {

    /**
     * 全局的fragment导航
     * 用的是PackRat\app\src\main\res\navigation\navigation_main.xml
     */
    protected val mainNavController by lazy {
        Navigation.findNavController(activity!!, R.id.activityRootFragment)
    }

    override fun onBackPressed(): Boolean {
        //执行全局导航的fragment返回栈
        mainNavController.popBackStack()
        return true
    }
}