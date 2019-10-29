package com.dididi.packrat.data.model.collect

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 27/10/2019
 * @describe 收藏界面的数据实体
 */

@Entity
data class Collect(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    //用户名
    val userId:String,
    //标题
    val title:String,
    //内容
    val content:BaseCollectContent,
    //日期
    val time:Long
)
