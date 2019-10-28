package com.dididi.packrat.data

import com.dididi.packrat.data.net.PackRatNetUtil


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 28/10/2019
 * @describe 我界面 数据依赖库 vm层通过此获取model层数据
 */

class MeRepository private constructor(private val net: PackRatNetUtil) {
    companion object {
        @Volatile
        private var instance: MeRepository? = null

        fun getInstance(net: PackRatNetUtil) = instance ?: synchronized(this) {
            instance ?: MeRepository(net).apply {
                instance = this
            }
        }
    }
}