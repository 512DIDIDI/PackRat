package com.dididi.packrat.framework.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dididi.packrat.core.domain.CollectContentType


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 27/10/2019
 * @describe 收藏界面的数据实体
 */

@Entity
data class Collect(
    /**
     * id 自增长
     */
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    /**
     * 隶属用户id
     */
    val userId: String,
    /**
     * 标题
     */
    val title: String,
    /**
     * 收藏数据类型
     */
    @CollectContentType
    val type: Int,
    /**
     * 收藏数据内容 通常是url地址或者文本信息
     */
    val content: String,
    /**
     * 收藏日期
     */
    val time: Long
) {
    constructor(userId: String, title: String, type: Int, content: String, time: Long) : this(
        0,
        userId,
        title,
        type,
        content,
        time
    )
}
