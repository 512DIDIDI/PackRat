package com.dididi.packrat.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.dididi.packrat.R


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 05/11/2019
 * @describe
 */

object DialogUtil {

    private val dialogs = arrayListOf<Dialog>()

    fun showLoading(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null, false)
        val dialog = Dialog(context, R.style.custom_dialog)
        dialog.setContentView(view)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(true)
        dialogs.add(dialog)
        dialog.show()

    }

    fun closeLoading() {
        dialogs.forEach {
            it.dismiss()
        }
    }
}