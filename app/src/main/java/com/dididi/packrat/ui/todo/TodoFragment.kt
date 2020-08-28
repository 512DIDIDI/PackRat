package com.dididi.packrat.ui.todo

import android.os.Bundle
import android.view.View
import com.dididi.packrat.R
import com.dididi.packrat.ui.BaseFragment
import com.dididi.packrat.ui.getHomeNav


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 24/10/2019
 * @describe
 */

class TodoFragment : BaseFragment() {

    override fun setLayout() = R.layout.fragment_todolist

    override fun bindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun doBusiness() {

    }

    override fun onBackPressed(): Boolean {
        getHomeNav(requireActivity()).popBackStack()
        return true
    }
}