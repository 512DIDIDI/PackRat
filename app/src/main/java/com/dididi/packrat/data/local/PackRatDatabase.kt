@file:Suppress("ReplaceArrayOfWithLiteral")

package com.dididi.packrat.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dididi.packrat.data.model.collect.Collect
import kotlinx.coroutines.CoroutineScope


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 28/10/2019
 * @describe 本地数据库
 */

@Database(
    //数据库表
    entities = arrayOf(Collect::class),
    //数据库版本号
    version = 1
)
abstract class PackRatDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: PackRatDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope) = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                PackRatDatabase::class.java,
                "pack_rat_database"
            ).build().apply {
                instance = this
            }
        }
    }

    abstract fun collectDao(): CollectDao
}