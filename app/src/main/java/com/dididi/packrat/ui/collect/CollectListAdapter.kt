package com.dididi.packrat.ui.collect

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dididi.packrat.R
import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.data.model.collect.CollectContentType
import com.dididi.packrat.utils.showPopupMenu
import com.tencent.smtt.sdk.WebView


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 04/11/2019
 * @describe 收藏界面recyclerview数据
 */

class CollectListAdapter(val context: Context) :
    RecyclerView.Adapter<CollectListAdapter.ViewHolder>(), PopupMenu.OnMenuItemClickListener {

    var collectList = mutableListOf<Collect>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_collect, parent, false))

    override fun getItemCount() = collectList.size

    override fun getItemViewType(position: Int) = collectList[position].type

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = collectList[position].title
        //隐藏所有的内容
        setContentGone(holder)
        //根据数据类型，显示不同的内容
        when (getItemViewType(position)) {
            CollectContentType.WEB.value -> {
                holder.webContent.visibility = View.VISIBLE
                holder.webContent.loadUrl(collectList[position].content)
            }
            CollectContentType.TEXT.value -> {
                holder.textContent.visibility = View.VISIBLE
                holder.textContent.text = collectList[position].content
            }
            else -> setContentGone(holder)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout = itemView.findViewById<CardView>(R.id.itemCollectCv)
        val title = itemView.findViewById<AppCompatTextView>(R.id.itemCollectTitle)
        val more = itemView.findViewById<AppCompatImageView>(R.id.itemCollectMore)
        val webContent = itemView.findViewById<WebView>(R.id.itemCollectWebContent)
        val textContent = itemView.findViewById<AppCompatTextView>(R.id.itemCollectTextContent)

        init {
            title.setOnClickListener {
                Toast.makeText(context, "点击标题", Toast.LENGTH_SHORT).show()
            }
            more.setOnClickListener {
                context.showPopupMenu(it, R.menu.menu_collect_more)
                    .setOnMenuItemClickListener(this@CollectListAdapter)
            }
            layout.setOnClickListener {
                Toast.makeText(
                    context,
                    "点击进入${collectList[layoutPosition].title}详情页",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem?) = when (item?.itemId) {
        R.id.menuCollectMoreTop -> {
            Toast.makeText(context, "点击置顶", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menuCollectMoreAddList -> {
            Toast.makeText(context, "点击添加到清单", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menuCollectMoreShare -> {
            Toast.makeText(context, "点击分享", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menuCollectMoreDelete -> {
            Toast.makeText(context, "点击删除", Toast.LENGTH_SHORT).show()
            true
        }
        else -> false
    }

    fun insertCollect(collect: Collect) {
        this.collectList.add(collect)
        notifyDataSetChanged()
    }

    /**
     * 隐藏所有的content控件
     */
    private fun setContentGone(holder: ViewHolder) {}
}