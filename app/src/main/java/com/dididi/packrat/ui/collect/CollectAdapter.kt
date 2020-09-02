package com.dididi.packrat.ui.collect

import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dididi.packrat.R
import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.data.model.collect.CollectContentType
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
            ?.addItemType(CollectContentType.TEXT.value, R.layout.item_collect_text)
            ?.addItemType(CollectContentType.AUDIO.value, R.layout.item_collect_audio)
            ?.addItemType(CollectContentType.IMAGE.value, R.layout.item_collect_image)
            ?.addItemType(CollectContentType.WEB.value, R.layout.item_collect_web)
            ?.addItemType(CollectContentType.VIDEO.value, R.layout.item_collect_video)
    }

    override fun convert(holder: BaseViewHolder, item: Collect) {
        when (holder.itemViewType) {
            CollectContentType.TEXT.value -> {
                holder.setText(R.id.itemCollectTextContent,item.content)
                    .setText(R.id.itemCollectTextTitle,item.title)
            }
            CollectContentType.AUDIO.value -> {
            }
            CollectContentType.IMAGE.value -> {
            }
            CollectContentType.WEB.value -> {
            }
            CollectContentType.VIDEO.value -> {
            }
            else -> throw IllegalArgumentException("no such type please use CollectContentType's type")
        }
    }
}