package com.dididi.packrat.ui

import android.app.Activity
import androidx.navigation.Navigation
import com.dididi.packrat.R


/**
 * @author dididi(yechao)
 * @since 28/08/2020
 * @describe 获取navigation导航
 */

/**
 * 获取主导航，主要是负责 LoginFragment - HomeFragment - CollectEditFragment 的导航
 */
fun getMainNav(activity:Activity) = Navigation.findNavController(activity, R.id.activityRootFragment)

/**
 * 获取首页导航，主要是负责 CollectFragment - TodoFragment - MeFragment 的导航
 */
fun getHomeNav(activity: Activity) = Navigation.findNavController(activity, R.id.fragmentMainFragment)