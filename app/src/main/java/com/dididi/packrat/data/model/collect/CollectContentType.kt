package com.dididi.packrat.data.model.collect

import android.os.Parcelable


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 29/10/2019
 * @describe 收藏页面内容类别
 */

enum class CollectContentType(val value: Int) {
    //图片收藏
    IMAGE(0),
    //视频收藏
    VIDEO(1),
    //录音音乐收藏
    AUDIO(2),
    //网页收藏
    WEB(3),
    //文本收藏
    TEXT(4),
    //文件收藏
    FILE(5)
}