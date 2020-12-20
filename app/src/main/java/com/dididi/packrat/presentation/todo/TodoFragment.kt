package com.dididi.packrat.presentation.todo

import com.dididi.packrat.R
import com.dididi.packrat.presentation.BaseFragment
import com.dididi.packrat.presentation.getHomeNav


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 24/10/2019
 * @describe
 */

class TodoFragment : BaseFragment() {

    override fun setLayout() = R.layout.fragment_todolist

    override fun doBusiness() {

    }

    override fun onBackPressed(): Boolean {
        getHomeNav(requireActivity()).popBackStack()
        return true
    }
}