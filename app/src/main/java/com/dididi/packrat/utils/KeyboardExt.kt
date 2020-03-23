package com.dididi.packrat.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * @author dididi(yechao)
 * @since 23/03/2020
 * @describe 软键盘工具类
 */


/**
 * 展开软键盘
 */
fun showSoftInput(et: EditText) {
    CoroutineScope(Dispatchers.Main).launch {
        et.isFocusable = true
        et.isFocusableInTouchMode = true
        et.requestFocus()
        val imm =
            et.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(et, 0)
    }
}

/**
 * 关闭软键盘
 */
fun Activity.closeSoftInput() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.window.decorView.windowToken, 0)
}

/**
 * 更改软键盘状态
 */
fun Activity.changeSoftInputStatus() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (imm.isActive) {
        imm.hideSoftInputFromWindow(
            this.currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}

