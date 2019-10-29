package com.dididi.packrat.data.model.collect

import android.os.Parcel
import android.os.Parcelable


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 29/10/2019
 * @describe 收藏视频信息
 */

class CollectVideoContent(var videoUrl:String) : BaseCollectContent() {
    override var type = CollectContentType.VIDEO.value

    constructor(parcel: Parcel) : this(parcel.readString()!!) {
        type = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(videoUrl)
        parcel.writeInt(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CollectVideoContent> {
        override fun createFromParcel(parcel: Parcel): CollectVideoContent {
            return CollectVideoContent(parcel)
        }

        override fun newArray(size: Int): Array<CollectVideoContent?> {
            return arrayOfNulls(size)
        }
    }

}