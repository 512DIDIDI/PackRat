package com.dididi.packrat.ui.collect

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dididi.packrat.R
import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.data.model.collect.CollectContentType
import com.dididi.packrat.ui.BaseMainNavFragment
import com.dididi.packrat.ui.widget.WithIconTextView
import com.dididi.packrat.utils.*
import kotlinx.android.synthetic.main.fragment_collect_edit.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * @author dididi(yechao)
 * @since 20/03/2020
 * @describe 收藏编辑页
 */

class CollectEditFragment : BaseMainNavFragment() {

    private val viewModel by lazy {
        ViewModelProvider(activity!!).get(CollectViewModel::class.java)
    }

    /**
     * 收藏的数据类型，默认是文本类型，根据左下角选择的类型来判断[CollectContentType]
     */
    private var collectContentType: CollectContentType = CollectContentType.TEXT

    override fun setLayout() = R.layout.fragment_collect_edit

    override fun bindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun doBusiness() {
        showSoftInput(fragmentCollectEditTitleEt)
        val dateFormat = SimpleDateFormat("MM-dd HH:mm", Locale.CHINA)
        fragmentCollectEditBottomBarTimeTv.text = dateFormat.format(System.currentTimeMillis())
        clickEvent()
        observe()
    }

    private fun clickEvent() {
        fragmentCollectEditBackBtn.setOnClickListener {
            activity?.closeSoftInput()
            mainNavController.navigateUp()
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
            mainNavController.navigateUp()
            activity?.closeSoftInput()
        }
        fragmentCollectEditBottomBarToolsBtn.setOnClickListener {
            showToolsPopupWindow(it)
        }
    }

    private fun showToolsPopupWindow(parentView: View) {
        val popupWindow = initPopupWindow(R.layout.dialog_collect_edit_tools).apply {
            showAtMiddleTop(parentView)
        }
        val view = popupWindow.contentView
        val camera = view.findViewById<WithIconTextView>(R.id.dialogCollectEditToolsCamera)
        val paint = view.findViewById<WithIconTextView>(R.id.dialogCollectEditToolsPaint)
        val record = view.findViewById<WithIconTextView>(R.id.dialogCollectEditToolsRecord)
        val web = view.findViewById<WithIconTextView>(R.id.dialogCollectEditToolsWeb)
        val gallery = view.findViewById<WithIconTextView>(R.id.dialogCollectEditToolsGallery)
        val chooseVideo =
            view.findViewById<WithIconTextView>(R.id.dialogCollectEditToolsChooseVideo)
        val chooseFile = view.findViewById<WithIconTextView>(R.id.dialogCollectEditToolsChooseFile)
        camera.setOnClickListener {
            collectContentType = CollectContentType.IMAGE
            toast("camera")
            popupWindow.dismiss()
        }
        paint.setOnClickListener {
            collectContentType = CollectContentType.IMAGE
            toast("paint")
            popupWindow.dismiss()
        }
        record.setOnClickListener {
            collectContentType = CollectContentType.AUDIO
            toast("record")
            popupWindow.dismiss()
        }
        web.setOnClickListener {
            collectContentType = CollectContentType.WEB
            toast("web")
            popupWindow.dismiss()
        }
        gallery.setOnClickListener {
            collectContentType = CollectContentType.IMAGE
            toast("gallery")
            popupWindow.dismiss()
        }
        chooseVideo.setOnClickListener {
            collectContentType = CollectContentType.VIDEO
            toast("chooseVideo")
            popupWindow.dismiss()
        }
        chooseFile.setOnClickListener {
            collectContentType = CollectContentType.FILE
            toast("chooseFile")
            popupWindow.dismiss()
        }
    }

    private fun observe() {
    }
}