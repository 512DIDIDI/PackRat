package com.dididi.packrat.ui.collect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dididi.packrat.data.CollectRepository


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 28/10/2019
 * @describe 收藏ViewModel的工厂类
 */

class CollectViewModelFactory(private val repository: CollectRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CollectViewModel(repository) as T
    }
}