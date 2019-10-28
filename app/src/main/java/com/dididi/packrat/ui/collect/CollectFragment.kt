package com.dididi.packrat.ui.collect

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dididi.packrat.PackRatApp
import com.dididi.packrat.R
import com.dididi.packrat.data.CollectRepository
import com.dididi.packrat.data.local.CollectDao
import com.dididi.packrat.data.local.PackRatDatabase
import com.dididi.packrat.data.net.PackRatNetUtil
import com.dididi.packrat.ui.BaseFragment


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 23/10/2019
 * @describe
 */

class CollectFragment : BaseFragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(
            this, CollectViewModelFactory(
                CollectRepository.getInstance(
                    PackRatDatabase.getInstance(PackRatApp.context).collectDao(),
                    PackRatNetUtil.getInstance()
                )
            )
        ).get(CollectViewModel::class.java)
    }

    override fun setLayout() = R.layout.fragment_collect

    override fun bindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun bindChildView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe()
    }

    private fun observe() {
        viewModel.dataChangeLiveData.observe(this, Observer {

        })

        viewModel.isLoading.observe(this, Observer {

        })
        if (viewModel.dataList.isEmpty()){
            viewModel.getCollects()
        }
    }
}