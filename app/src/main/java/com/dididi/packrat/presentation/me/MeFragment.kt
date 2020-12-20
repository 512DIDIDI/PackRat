package com.dididi.packrat.presentation.me

import com.dididi.packrat.R
import com.dididi.packrat.presentation.BaseFragment
import com.dididi.packrat.presentation.getHomeNav


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 23/10/2019
 * @describe
 */

class MeFragment : BaseFragment() {
    override fun setLayout() = R.layout.fragment_me

    override fun doBusiness() {

    }

    override fun onBackPressed(): Boolean {
        getHomeNav(requireActivity()).popBackStack()
        return true
    }
}