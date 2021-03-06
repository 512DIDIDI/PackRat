package com.dididi.packrat.framework.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


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

    companion object {
        @Volatile
        private var instance: PackRatDatabase? = null

        fun getInstance(context: Context): PackRatDatabase {
            return instance ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context,
                    PackRatDatabase::class.java,
                    "pack_rat_database"
                )
                    .build()
                instance = database
                instance!!
            }
        }
    }


}