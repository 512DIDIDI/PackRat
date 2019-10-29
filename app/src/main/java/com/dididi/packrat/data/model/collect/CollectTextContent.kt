package com.dididi.packrat.data.model.collect

import android.os.Parcel
import android.os.Parcelable


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 29/10/2019
 * @describe 收藏文本信息
 */

class CollectTextContent(var textContent: String) : BaseCollectContent() {
    override var type = CollectContentType.TEXT.value

    constructor(parcel: Parcel) : this(parcel.readString()!!) {
        type = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(textContent)
        parcel.writeInt(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CollectTextContent> {
        override fun createFromParcel(parcel: Parcel): CollectTextContent {
            return CollectTextContent(parcel)
        }

        override fun newArray(size: Int): Array<CollectTextContent?> {
            return arrayOfNulls(size)
        }
    }

}