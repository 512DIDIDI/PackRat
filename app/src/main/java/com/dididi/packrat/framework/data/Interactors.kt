package com.dididi.packrat.framework.data

import com.dididi.packrat.core.interactors.collect.AddCollect
import com.dididi.packrat.core.interactors.collect.AddCollects
import com.dididi.packrat.core.interactors.collect.GetCollect
import com.dididi.packrat.core.interactors.collect.RemoveCollect
import com.dididi.packrat.core.interactors.login.Login
import com.dididi.packrat.core.interactors.login.Logout
import com.dididi.packrat.core.interactors.login.Register

/**
 * author: yechao
 * desc: 交互器集合
 * createTime:2020-12-18
 */
data class Interactors(
    val addCollect: AddCollect,
    val addCollects: AddCollects,
    val getCollect: GetCollect,
    val removeCollect: RemoveCollect,
    val login: Login,
    val logout: Logout,
    val register: Register
)