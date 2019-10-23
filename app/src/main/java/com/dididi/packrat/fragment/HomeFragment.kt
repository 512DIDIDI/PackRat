package com.dididi.packrat.fragment

import android.os.Bundle
import android.view.View
import com.dididi.packrat.R


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 23/10/2019
 * @describe
 */

class HomeFragment :BaseFragment(){
    override fun setLayout() = R.layout.fragment_home

    override fun bindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun bindChildView(savedInstanceState: Bundle?, rootView: View) {
    }
}