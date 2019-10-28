package com.dididi.packrat.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dididi.packrat.data.model.collect.Collect


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 28/10/2019
 * @describe 收藏页面数据表(增删改查本地数据库)
 */

@Dao
interface CollectDao {

    //获取collect list的liveData数据
    @Query("SELECT * FROM Collect ORDER BY id ASC")
    fun getCollectList(): LiveData<List<Collect>>

    //插入数据 冲突则替换
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(collect: Collect)

    @Query("DELETE FROM Collect")
    suspend fun deleteAll()
}