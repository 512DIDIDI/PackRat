package com.dididi.packrat.data.net.api

import com.dididi.packrat.data.model.collect.Collect
import retrofit2.Call
import retrofit2.http.GET


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 28/10/2019
 * @describe 收藏页面调取的api接口
 */

interface CollectService {
    @GET()
    fun getCollect():Call<MutableList<Collect>>
}