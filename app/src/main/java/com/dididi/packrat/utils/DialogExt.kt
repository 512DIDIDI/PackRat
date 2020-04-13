package com.dididi.packrat.utils

import android.app.Dialog
import android.content.Context
import android.icu.util.Measure
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.PopupWindow
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import com.dididi.packrat.R


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 05/11/2019
 * @describe 弹窗拓展类
 */

private val dialogs = arrayListOf<Dialog>()
private val popupMenus = arrayListOf<PopupMenu>()

/**
 * loading框显示
 */
fun Context.showLoading() = showLoading(R.layout.dialog_loading)

fun Fragment.showLoading() = activity!!.showLoading()

fun Context.showLoading(@LayoutRes layoutRes: Int) = Dialog(this, R.style.custom_dialog).apply {
    val view = LayoutInflater
        .from(this@showLoading)
        .inflate(layoutRes, null)
    setContentView(view)
    setCanceledOnTouchOutside(false)
    setCancelable(true)
    dialogs.add(this)
    show()
}

fun Fragment.showLoading(@LayoutRes layoutRes: Int) = activity!!.showLoading(layoutRes)

/**
 * 关闭所有弹出框
 */
fun dismissAllLoading() = dialogs.forEach {
    it.dismiss()
}

/**
 * 收藏页面popupMenu显示
 */
fun Context.showPopupMenu(parentView: View, @MenuRes menuRes: Int) =
    PopupMenu(this, parentView).apply {
        menuInflater.inflate(menuRes, menu)
        show()
    }

fun Fragment.showPopupMenu(parentView: View, @MenuRes menuRes: Int) =
    activity!!.showPopupMenu(parentView, menuRes)

/**
 * 初始化PopupWindow
 * 显示出来需要
 * 1.显示在某个指定控件的下方
 *   [showAsDropDown(View anchor)]
 *   [showAsDropDown(View anchor, int xoff, int yoff)]
 * 2.指定父视图，显示在父控件的某个位置（Gravity.TOP,Gravity.RIGHT等）
 *   showAtLocation(View parent, int gravity, int x, int y)
 * 3.显示在父控件正上方
 *   [showAtMiddleTop]
 */
fun Context.initPopupWindow(
    @LayoutRes layoutRes: Int,
    width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT
): PopupWindow {
    val itemView = LayoutInflater.from(this).inflate(layoutRes, null, false)
    val popupWindow = PopupWindow(itemView, width, height, true)
    itemView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
    return popupWindow
}

fun Fragment.initPopupWindow(
    @LayoutRes layoutRes: Int,
    width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT
) = activity!!.initPopupWindow(layoutRes, width, height)

/**
 * popupWindow显示在父控件正上方
 */
fun PopupWindow.showAtMiddleTop(parentView: View): PopupWindow {
    //选中父控件
    parentView.isSelected = true
    //获取父控件位置
    val location = IntArray(2)
    parentView.getLocationOnScreen(location)
    val popupView = this.contentView
    val popupWidth = popupView.measuredWidth
    val popupHeight = popupView.measuredHeight
    val xPos = location[0] + parentView.width / 2 - popupWidth / 2
    val yPos = location[1] - popupHeight
    showAtLocation(
        parentView,
        Gravity.NO_GRAVITY,
        //定位popupWindow左上角坐标
        xPos,
        yPos
    )
    setOnDismissListener {
        if (parentView.isSelected) {
            parentView.isSelected = false
        }
    }
    return this
}

