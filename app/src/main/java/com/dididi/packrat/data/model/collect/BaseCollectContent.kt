package com.dididi.packrat.data.model.collect

import android.os.Parcelable


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 29/10/2019
 * @describe 收藏页面内容
 */

abstract class BaseCollectContent : Parcelable{
    abstract var type:Int
}