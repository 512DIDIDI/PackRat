package com.dididi.packrat.data.model.collect

import android.os.Parcel
import android.os.Parcelable


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 29/10/2019
 * @describe 收藏网页信息
 */

class CollectWebContent(var webUrl: String) : BaseCollectContent() {
    override var type = CollectContentType.WEB.value

    constructor(parcel: Parcel) : this(parcel.readString()!!) {
        type = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(webUrl)
        parcel.writeInt(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CollectWebContent> {
        override fun createFromParcel(parcel: Parcel): CollectWebContent {
            return CollectWebContent(parcel)
        }

        override fun newArray(size: Int): Array<CollectWebContent?> {
            return arrayOfNulls(size)
        }
    }

}