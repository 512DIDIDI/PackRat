package com.dididi.packrat.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupMenu
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
fun Context.showLoading() = Dialog(this, R.style.custom_dialog).apply {
    val view = LayoutInflater
        .from(this@showLoading)
        .inflate(R.layout.dialog_loading, null, false)
    setContentView(view)
    setCanceledOnTouchOutside(false)
    setCancelable(true)
    dialogs.add(this)
    show()
}

fun Fragment.showLoading() = this.activity!!.showLoading()

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
    this.activity!!.showPopupMenu(parentView, menuRes)

/**
 * 关闭所有popupmenu
 */
fun dismissAllPopupMenu() = popupMenus.forEach {
    it.dismiss()
}