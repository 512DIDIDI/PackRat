package com.dididi.packrat.core.data

import com.dididi.packrat.core.domain.Collect

/**
 * author: yechao
 * desc: 收藏数据接口
 * createTime:2020-12-18
 */
interface ICollectDataSource {
    suspend fun addCollect(collect:Collect)

    suspend fun addCollects(collects:List<Collect>)

    suspend fun getCollect():List<Collect>

    suspend fun removeCollect()
}