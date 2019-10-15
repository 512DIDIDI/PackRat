package com.dididi.tbsfiledemo.utils

import android.util.Log
import com.dididi.tbsfiledemo.BuildConfig


/**
 * @author dididi(yechao)
 * @since 09/10/2019
 * @describe 日志工具类
 */

class LogUtil {
    companion object {
        private const val TAG = "TbsFileDemo"
        private const val LOG_MAX_LENGTH = 2000
        private var debug = BuildConfig.DEBUG
        fun d(tips: String) {
            if (debug) {
                //增加log日志的长度
                val strLength = tips.length
                var start = 0
                var end = LOG_MAX_LENGTH
                for (i in 0..100) {
                    if (strLength > end) {
                        Log.d(TAG, tips.substring(start, end))
                        start = end
                        end += LOG_MAX_LENGTH
                    } else {
                        Log.d(TAG, tips.substring(start, strLength))
                        break
                    }
                }
            }
        }
    }
}