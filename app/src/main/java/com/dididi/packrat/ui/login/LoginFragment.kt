package com.dididi.packrat.ui.login

import android.os.Bundle
import android.view.View
import com.dididi.packrat.R
import com.dididi.packrat.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * @author dididi(yechao)
 * @since 10/12/2019
 * @describe 登录页
 */

class LoginFragment : BaseFragment() {
    override fun setLayout() = R.layout.fragment_login

    override fun bindView(savedInstanceState: Bundle?, rootView: View) {
        fragmentLoginEnterSign.setOnClickListener {
            fragmentLoginEnterSign.playAnimation()
        }
    }

    override fun bindChildView(savedInstanceState: Bundle?, rootView: View) {
    }
}