@file:Suppress("UNUSED_PARAMETER")

package com.dididi.packrat.utils

import java.io.File


/**
 * @author dididi(yechao)
 * @since 09/10/2019
 * @describe 文件拓展类
 */

/**
 * 获取文件类型
 * @return 如果是文件夹，返回folder，否则返回后缀名
 */
fun File.getFileType() = if (!this.getFileName().contains(".")) {
    "folder"
} else {
    this.getFileName().substring(this.getFileName().lastIndexOf(".") + 1)
}

/**
 * 获取文件名
 * @return 返回文件名，带后缀名
 */
fun File.getFileName() = this.absolutePath.substring(this.absolutePath.lastIndexOf("/") + 1)