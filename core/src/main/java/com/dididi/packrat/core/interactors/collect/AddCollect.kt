package com.dididi.packrat.core.interactors.collect

import com.dididi.packrat.core.data.ICollectDataSource
import com.dididi.packrat.core.domain.Collect

/**
 * author: yechao
 * desc: 交互器 - 添加收藏
 * createTime:2020-12-18
 */
class AddCollect(private val ICollectDataSource: ICollectDataSource) {
    suspend operator fun invoke(collect: Collect) {
        ICollectDataSource.addCollect(collect)
    }
}