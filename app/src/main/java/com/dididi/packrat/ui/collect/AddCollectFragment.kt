package com.dididi.packrat.ui.collect

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.dididi.packrat.R
import com.dididi.packrat.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_add_collect.*


/**
 * @author dididi(yechao)
 * @since 20/03/2020
 * @describe 添加收藏页
 */

class AddCollectFragment :BaseFragment() {
    override fun setLayout() = R.layout.fragment_add_collect

    override fun bindView(savedInstanceState: Bundle?, rootView: View) {
        clickEvent()
    }

    private fun clickEvent(){
        fragmentAddCollectBackBtn.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
    }
}