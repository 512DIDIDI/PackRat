@file:Suppress("UNUSED_PARAMETER")

package com.dididi.tbsfiledemo.utils


/**
 * @author dididi(yechao)
 * @since 09/10/2019
 * @describe 文件工具类
 */

class FileUtil {
    companion object {

        /**
         * 获取文件类型
         * 如果是文件类型返回folder，否则返回后缀名
         */
        fun getFileType(fileName: String) = if (!fileName.contains(".")) {
            "folder"
        } else {
            fileName.substring(fileName.lastIndexOf(".") + 1)
        }

        /**
         * 获取文件名(针对android路径)
         */
        fun getFileName(filePath: String) = filePath.substring(filePath.lastIndexOf("/") + 1)

        fun getFileIcon(fileName: String){

        }
    }

}