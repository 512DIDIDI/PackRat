package com.dididi.packrat.ui.collect

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.dididi.packrat.R
import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.data.model.collect.CollectContentType
import com.dididi.packrat.ui.BaseMainNavFragment
import com.dididi.packrat.utils.closeSoftInput
import com.dididi.packrat.utils.onSingleClick
import com.dididi.packrat.utils.showSoftInput
import kotlinx.android.synthetic.main.fragment_add_collect.*


/**
 * @author dididi(yechao)
 * @since 20/03/2020
 * @describe 添加收藏页
 */

class AddCollectFragment : BaseMainNavFragment() {

    private val viewModel by lazy {
        ViewModelProvider(activity!!).get(CollectViewModel::class.java)
    }

    /**
     * 收藏的数据类型，默认是文本类型，根据左下角选择的类型来判断[CollectContentType]
     */
    private var collectContentType: CollectContentType = CollectContentType.TEXT

    override fun setLayout() = R.layout.fragment_add_collect

    override fun bindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun doBusiness() {
        showSoftInput(fragmentAddCollectTitleEt)
        clickEvent()
        observe()
    }

    private fun clickEvent() {
        fragmentAddCollectBackBtn.setOnClickListener {
            activity?.closeSoftInput()
            mainNavController.navigateUp()
        }
        fragmentAddCollectSaveBtn.onSingleClick {
            val collect = Collect(
                "dididi",
                fragmentAddCollectTitleEt.text.toString(),
                collectContentType.value,
                fragmentAddCollectContentEt.text.toString(),
                System.currentTimeMillis()
            )
            viewModel.setCollect(collect)
            mainNavController.navigateUp()
            activity?.closeSoftInput()
        }
    }

    private fun observe() {
    }
}