package com.dididi.packrat.framework.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 28/10/2019
 * @describe 收藏页面数据表(增删改查本地数据库)
 */

@Dao
interface CollectDao {

    //获取collect list的liveData数据
    @Query("SELECT * from Collect ORDER BY id ASC")
    fun getCollectList(): List<Collect>

    //插入数据 冲突则替换
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(collect: Collect)

    @Query("DELETE from Collect")
    suspend fun deleteAll()
}