package com.dididi.packrat.data.model.collect

import android.os.Parcel
import android.os.Parcelable


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 29/10/2019
 * @describe 收藏页面图片消息
 */

class CollectImageContent(var imageUrl: String) : BaseCollectContent() {

    override var type = CollectContentType.IMAGE.value

    constructor(parcel: Parcel) : this(parcel.readString()!!) {
        type = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imageUrl)
        parcel.writeInt(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CollectImageContent> {
        override fun createFromParcel(parcel: Parcel): CollectImageContent {
            return CollectImageContent(parcel)
        }

        override fun newArray(size: Int): Array<CollectImageContent?> {
            return arrayOfNulls(size)
        }
    }


}