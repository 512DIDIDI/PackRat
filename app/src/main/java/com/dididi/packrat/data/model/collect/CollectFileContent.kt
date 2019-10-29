package com.dididi.packrat.data.model.collect

import android.os.Parcel
import android.os.Parcelable


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 29/10/2019
 * @describe 收藏文件信息
 */

class CollectFileContent(var filePath: String) : BaseCollectContent() {

    override var type = CollectContentType.FILE.value

    constructor(parcel: Parcel) : this(parcel.readString()!!) {
        type = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(filePath)
        parcel.writeInt(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CollectFileContent> {
        override fun createFromParcel(parcel: Parcel): CollectFileContent {
            return CollectFileContent(parcel)
        }

        override fun newArray(size: Int): Array<CollectFileContent?> {
            return arrayOfNulls(size)
        }
    }

}