@file:Suppress("ReplaceArrayOfWithLiteral")

package com.dididi.packrat.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.data.model.collect.CollectContentType
import com.dididi.packrat.utils.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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

    abstract fun collectDao(): CollectDao

    private class PackRatDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
        }
    }

    companion object {
        @Volatile
        private var instance: PackRatDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): PackRatDatabase {
            return instance ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context,
                    PackRatDatabase::class.java,
                    "pack_rat_database"
                )
                    .addCallback(PackRatDatabaseCallback(scope))
                    .build()
                instance = database
                instance!!
            }
        }
    }


}