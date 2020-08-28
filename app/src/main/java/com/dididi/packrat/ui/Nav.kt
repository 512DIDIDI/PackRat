package com.dididi.packrat.ui

import android.app.Activity
import androidx.navigation.Navigation
import com.dididi.packrat.R


/**
 * @author dididi(yechao)
 * @since 28/08/2020
 * @describe 获取navigation导航
 */

fun getMainNav(activity:Activity) = Navigation.findNavController(activity, R.id.activityRootFragment)

fun getHomeNav(activity: Activity) = Navigation.findNavController(activity, R.id.fragmentMainFragment)