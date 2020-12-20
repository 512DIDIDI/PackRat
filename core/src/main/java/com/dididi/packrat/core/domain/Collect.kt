package com.dididi.packrat.core.domain

/**
 * author: yechao
 * desc:
 * createTime:2020-12-18
 */

data class Collect(
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
)
