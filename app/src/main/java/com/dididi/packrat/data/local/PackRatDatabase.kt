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
            instance?.let {
                scope.launch(Dispatchers.IO) {
                    val collectDao = it.collectDao()
                    collectDao.deleteAll()
                    collectDao.insert(
                        Collect(
                            1,
                            "dididi",
                            "取消协程的执行",
                            CollectContentType.TEXT.value,
                            "在一个长时间运行的应用程序中，你也许需要对你的后台协程进行细粒度的控制。 比如说，一个用户也许关闭了一个启动了协程的界面，那么现在协程的执行结果已经不再被需要了，这时，它应该是可以被取消的。 该 launch 函数返回了一个可以被用来取消运行中的协程的 Job：",
                            222L
                        )
                    )
                    collectDao.insert(
                        Collect(
                            2,
                            "dididi",
                            "user",
                            CollectContentType.WEB.value,
                            "https://www.jianshu.com",
                            333L
                        )
                    )
                    collectDao.getCollectList().forEach {
                        log(content = "insert:${it.content}")
                    }
                }
            }
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