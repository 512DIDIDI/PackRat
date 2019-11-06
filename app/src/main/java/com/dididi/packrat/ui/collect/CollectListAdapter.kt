package com.dididi.packrat.ui.collect

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dididi.packrat.R
import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.utils.DialogUtil
import com.tencent.smtt.sdk.WebView


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 04/11/2019
 * @describe 收藏界面recyclerview数据
 */

class CollectListAdapter(val context: Context) :
    RecyclerView.Adapter<CollectListAdapter.ViewHolder>() {

    var collectList = emptyList<Collect>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_collect, parent, false))

    override fun getItemCount() = collectList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = collectList[position].title
        holder.content.loadUrl(collectList[position].content)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout = itemView.findViewById<CardView>(R.id.itemListCollectCv)
        val title = itemView.findViewById<AppCompatTextView>(R.id.itemListCollectTitle)
        val more = itemView.findViewById<AppCompatImageView>(R.id.itemListCollectMore)
        val content = itemView.findViewById<WebView>(R.id.itemListCollectContent)

        init {
            title.setOnClickListener {
                Toast.makeText(context, "点击标题", Toast.LENGTH_SHORT).show()
            }
            more.setOnClickListener {
                DialogUtil.showPopupMenu(context, it)
            }
            layout.setOnClickListener {
                Toast.makeText(context, "点击进入详情页", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun setData(collectList: List<Collect>) {
        this.collectList = collectList
        notifyDataSetChanged()
    }


}