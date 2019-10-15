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
         */
        fun getFileType(fileName: String) = if (!fileName.contains(".")) {
            "folder"
        } else {
            fileName.substring(fileName.lastIndexOf(".") + 1)
        }

        /**
         * 获取文件名
         */
        fun getFileName(filePath: String) = filePath.substring(filePath.lastIndexOf("/") + 1)

        fun getFileIcon(fileName: String){

        }
    }

}