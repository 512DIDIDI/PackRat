package com.dididi.packrat.core.domain

import com.dididi.packrat.core.annotation.IntDef
import com.dididi.packrat.core.domain.CollectContentType.Companion.AUDIO
import com.dididi.packrat.core.domain.CollectContentType.Companion.IMAGE
import com.dididi.packrat.core.domain.CollectContentType.Companion.TEXT
import com.dididi.packrat.core.domain.CollectContentType.Companion.VIDEO
import com.dididi.packrat.core.domain.CollectContentType.Companion.WEB

/**
 * author: yechao
 * desc: 收藏页面内容类别
 * createTime:2020-12-18
 */

@IntDef(IMAGE, VIDEO, AUDIO, WEB, TEXT)
annotation class CollectContentType() {
    companion object {
        //图片收藏
        const val IMAGE = 0

        //视频收藏
        const val VIDEO = 1

        //录音音乐收藏
        const val AUDIO = 2

        //网页收藏
        const val WEB = 3

        //文本收藏
        const val TEXT = 4
    }
}
