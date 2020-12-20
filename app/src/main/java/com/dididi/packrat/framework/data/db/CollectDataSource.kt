package com.dididi.packrat.framework.data.db

import android.content.Context
import com.dididi.packrat.core.data.ICollectDataSource
import com.dididi.packrat.core.domain.Collect

/**
 * author: yechao
 * desc:
 * createTime:2020-12-18
 */
class CollectDataSource(context: Context) : ICollectDataSource {

    private val collectDao = PackRatDatabase.getInstance(context).collectDao()

    override suspend fun addCollect(collect: Collect) {
        TODO("Not yet implemented")
    }

    override suspend fun addCollects(collects: List<Collect>) {
        TODO("Not yet implemented")
    }

    override suspend fun getCollect(): List<Collect> {
        TODO("Not yet implemented")
    }

    override suspend fun removeCollect() {
        TODO("Not yet implemented")
    }
}