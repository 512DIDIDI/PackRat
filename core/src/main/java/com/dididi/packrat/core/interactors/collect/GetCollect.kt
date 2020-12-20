package com.dididi.packrat.core.interactors.collect

import com.dididi.packrat.core.data.ICollectDataSource
import com.dididi.packrat.core.domain.Collect

/**
 * author: yechao
 * desc: 交互器 - 获取收藏列表
 * createTime:2020-12-18
 */
class GetCollect(private val ICollectDataSource: ICollectDataSource){
    suspend operator fun invoke():List<Collect>{
        return ICollectDataSource.getCollect()
    }
}