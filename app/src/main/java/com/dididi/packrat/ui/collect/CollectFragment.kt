package com.dididi.packrat.ui.collect

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dididi.packrat.R
import com.dididi.packrat.ui.BaseFragment
import com.dididi.packrat.utils.DialogUtil
import kotlinx.android.synthetic.main.fragment_collect.*


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 23/10/2019
 * @describe
 */

class CollectFragment : BaseFragment() {

    private lateinit var mCollectAdapter: CollectListAdapter

    private val viewModel by lazy {
        ViewModelProviders.of(activity!!).get(CollectViewModel::class.java)
    }

    override fun setLayout() = R.layout.fragment_collect

    override fun bindView(savedInstanceState: Bundle?, rootView: View) {
        val layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
        fragmentCollectContentRv.layoutManager = layoutManager
        mCollectAdapter = CollectListAdapter(activity!!)
        fragmentCollectContentRv.adapter = mCollectAdapter
        //获取远程收藏数据
        viewModel.getRemoteCollects()
        observe()
    }

    override fun bindChildView(savedInstanceState: Bundle?, rootView: View) {
    }

    private fun observe() {
        //观察收藏数据变化
        viewModel.collectLiveData.observe(this, Observer {
            mCollectAdapter.updateData(it)
        })
        //观察loading值决定是否加载loading框
        viewModel.isLoading.observe(this, Observer {
            if (it) {
                DialogUtil.showLoading(activity!!)
            } else {
                DialogUtil.dismissLoading()
            }
        })
    }
}