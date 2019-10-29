package com.dididi.packrat.data.model.collect

import android.os.Parcel
import android.os.Parcelable


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 29/10/2019
 * @describe 收藏音频音乐信息
 */

class CollectAudioContent() :BaseCollectContent(){
    override var type = CollectContentType.AUDIO.value

    var audioUrl:String? = null

    constructor(parcel: Parcel) : this() {
        type = parcel.readInt()
        audioUrl = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(type)
        parcel.writeString(audioUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CollectAudioContent> {
        override fun createFromParcel(parcel: Parcel): CollectAudioContent {
            return CollectAudioContent(parcel)
        }

        override fun newArray(size: Int): Array<CollectAudioContent?> {
            return arrayOfNulls(size)
        }
    }
}