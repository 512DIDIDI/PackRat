package com.dididi.packrat.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupMenu
import com.dididi.packrat.R


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 05/11/2019
 * @describe
 */

object DialogUtil {

    private val dialogs = arrayListOf<Dialog>()
    private val popupMenus = arrayListOf<PopupMenu>()

    /**
     * loading框显示
     */
    fun showLoading(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null, false)
        val dialog = Dialog(context, R.style.custom_dialog)
        dialog.setContentView(view)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(true)
        dialogs.add(dialog)
        dialog.show()

    }

    fun dismissLoading() {
        dialogs.forEach {
            it.dismiss()
        }
    }

    /**
     * 收藏页面popupMenu显示
     */
    fun showPopupMenu(context: Context, parentView: View) =
        PopupMenu(context, parentView).apply {
            menuInflater.inflate(R.menu.menu_collect_more, menu)
            show()
        }

    fun dismissPopupMenu(){
        popupMenus.forEach {
            it.dismiss()
        }
    }
}