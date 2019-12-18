package com.dididi.packrat.utils

import android.content.Context
import android.graphics.Point
import android.view.WindowManager


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 18/12/2019
 * @describe 尺寸相关拓展类
 */

fun Context.getScreeSize() = Point().also {
    val wm = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    wm.defaultDisplay.getSize(it)
}