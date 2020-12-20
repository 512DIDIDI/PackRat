package com.dididi.packrat.core.interactors.collect

import com.dididi.packrat.core.data.ICollectDataSource

/**
 * author: yechao
 * desc: 交互器 - 移除收藏
 * createTime:2020-12-18
 */
class RemoveCollect(private val ICollectDataSource: ICollectDataSource) {
    suspend operator fun invoke() {
        ICollectDataSource.removeCollect()
    }
}