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

    private lateinit var viewModel: CollectViewModel

    override fun setLayout() = R.layout.fragment_collect

    override fun bindView(savedInstanceState: Bundle?, rootView: View) {
        val layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
        fragmentCollectContentRv.layoutManager = layoutManager
        mCollectAdapter = CollectListAdapter(activity!!)
        fragmentCollectContentRv.adapter = mCollectAdapter
        viewModel = ViewModelProviders.of(activity!!).get(CollectViewModel::class.java)
        viewModel.getRemoteCollects()
        observe()
    }

    override fun bindChildView(savedInstanceState: Bundle?, rootView: View) {
    }

    private fun observe() {
        viewModel.collectLiveData.observe(this, Observer {
            mCollectAdapter.setData(it)
        })
        viewModel.isLoading.observe(this, Observer {
            if (it) {
                DialogUtil.showLoading(activity!!)
            } else {
                DialogUtil.dismissLoading()
            }
        })
    }
}