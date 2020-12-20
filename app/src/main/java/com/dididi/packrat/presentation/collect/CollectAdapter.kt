package com.dididi.packrat.presentation.collect

import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dididi.packrat.R
import com.dididi.packrat.core.domain.CollectContentType
import com.dididi.packrat.framework.data.db.Collect
import java.lang.IllegalArgumentException


/**
 * @author dididi(叶超)
 * @email 512dididi@gmail.com
 * @since 30/08/2020
 * @describe 收藏页面的list适配器
 */

class CollectAdapter(collects: MutableList<Collect>?) :
    BaseDelegateMultiAdapter<Collect, BaseViewHolder>(collects) {

    init {
        //设置item类型
        setMultiTypeDelegate(object : BaseMultiTypeDelegate<Collect>() {
            override fun getItemType(data: List<Collect>, position: Int): Int {
                return data[position].type
            }
        })
        //绑定item与布局
        getMultiTypeDelegate()
            ?.addItemType(CollectContentType.TEXT, R.layout.item_collect_text)
            ?.addItemType(CollectContentType.AUDIO, R.layout.item_collect_audio)
            ?.addItemType(CollectContentType.IMAGE, R.layout.item_collect_image)
            ?.addItemType(CollectContentType.WEB, R.layout.item_collect_web)
            ?.addItemType(CollectContentType.VIDEO, R.layout.item_collect_video)
    }

    override fun convert(holder: BaseViewHolder, item: Collect) {
        when (holder.itemViewType) {
            CollectContentType.TEXT -> {
                holder.setText(R.id.itemCollectTextContent,item.content)
                    .setText(R.id.itemCollectTextTitle,item.title)
            }
            CollectContentType.AUDIO -> {
            }
            CollectContentType.IMAGE -> {
            }
            CollectContentType.WEB -> {
            }
            CollectContentType.VIDEO -> {
            }
            else -> throw IllegalArgumentException("no such type please use CollectContentType's type")
        }
    }
}