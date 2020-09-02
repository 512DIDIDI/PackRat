package com.dididi.packrat.ui.collect

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.dididi.packrat.R
import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.data.model.collect.CollectContentType
import com.dididi.packrat.ui.BaseFragment
import com.dididi.packrat.ui.getMainNav
import com.dididi.uiextlib.ext.closeSoftInput
import com.dididi.uiextlib.ext.onSingleClick
import com.dididi.uiextlib.ext.showSoftInput
import kotlinx.android.synthetic.main.fragment_collect_edit.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * @author dididi(yechao)
 * @since 20/03/2020
 * @describe 收藏编辑页
 */

class CollectEditFragment : BaseFragment() {

    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(CollectViewModel::class.java)
    }

    /**
     * 收藏的数据类型，默认是文本类型，根据左下角选择的类型来判断[CollectContentType]
     */
    private var collectContentType: CollectContentType = CollectContentType.TEXT

    override fun setLayout() = R.layout.fragment_collect_edit

    override fun doBusiness() {
        showSoftInput(fragmentCollectEditTitleEt)
        val dateFormat = SimpleDateFormat("MM-dd HH:mm", Locale.CHINA)
        fragmentCollectEditBottomBarTimeTv.text = dateFormat.format(System.currentTimeMillis())
        clickEvent()
        observe()
    }

    override fun onBackPressed(): Boolean {
        //执行全局导航的fragment返回栈
        getMainNav(requireActivity()).popBackStack()
        return true
    }

    private fun clickEvent() {
        fragmentCollectEditBackBtn.setOnClickListener {
            activity?.closeSoftInput()
            getMainNav(requireActivity()).navigateUp()
        }
        fragmentCollectEditSaveBtn.onSingleClick {
            val collect = Collect(
                "dididi",
                fragmentCollectEditTitleEt.text.toString(),
                collectContentType.value,
                fragmentCollectEditContentEt.text.toString(),
                System.currentTimeMillis()
            )
            viewModel.setCollect(collect)
            getMainNav(requireActivity()).navigateUp()
            activity?.closeSoftInput()
        }
    }

    private fun observe() {
    }
}