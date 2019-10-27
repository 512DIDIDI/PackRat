package com.dididi.packrat.utils

import android.util.Log
import com.dididi.packrat.BuildConfig


/**
 * @author dididi(yechao)
 * @since 09/10/2019
 * @describe 日志工具类
 */

object LogUtil{
    private const val TAG = "PackRat"
    //最长日志行数
    private const val LOG_MAX_LENGTH = 2000
    private var debug = BuildConfig.DEBUG

    /**
     * 输入长日志
     * @param content 日志内容
     */
    fun debug(content: String){
        if (debug) {
            //增加log日志的长度
            val strLength = content.length
            var start = 0
            var end = LOG_MAX_LENGTH
            for (i in 0..100) {
                if (strLength > end) {
                    Log.d(TAG, content.substring(start, end))
                    start = end
                    end += LOG_MAX_LENGTH
                } else {
                    Log.d(TAG, content.substring(start, strLength))
                    break
                }
            }
        }
    }
}


