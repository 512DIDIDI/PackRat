package com.dididi.packrat.ui.me

import android.os.Bundle
import android.view.View
import com.dididi.packrat.R
import com.dididi.packrat.ui.BaseFragment
import com.dididi.packrat.ui.getHomeNav


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 23/10/2019
 * @describe
 */

class MeFragment : BaseFragment() {
    override fun setLayout() = R.layout.fragment_me

    override fun bindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun doBusiness() {

    }

    override fun onBackPressed(): Boolean {
        getHomeNav(requireActivity()).popBackStack()
        return true
    }
}