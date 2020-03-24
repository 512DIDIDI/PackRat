package com.dididi.packrat.ui.collect

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dididi.packrat.R
import com.dididi.packrat.ui.BaseHomeNavFragment
import com.dididi.packrat.utils.dismissAllLoading
import com.dididi.packrat.utils.onSingleClick
import com.dididi.packrat.utils.showLoading
import kotlinx.android.synthetic.main.fragment_collect.*


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 23/10/2019
 * @describe
 */

class CollectFragment : BaseHomeNavFragment() {

    private lateinit var mCollectAdapter: CollectListAdapter

    private val viewModel by lazy {
        ViewModelProvider(activity!!).get(CollectViewModel::class.java)
    }

    override fun setLayout() = R.layout.fragment_collect

    override fun bindView(savedInstanceState: Bundle?, rootView: View) {
        val layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
        fragmentCollectContentRv.layoutManager = layoutManager
        mCollectAdapter = CollectListAdapter(activity!!)
        fragmentCollectContentRv.adapter = mCollectAdapter
    }

    override fun onBackPressed() = false

    override fun doBusiness() {
        observe()
        //获取数据
        viewModel.getCollects()
        clickEvent()
    }

    private fun clickEvent() {
        fragmentCollectFab.onSingleClick(500L) {
            mainNavController.navigate(R.id.action_home_to_addCollect)
        }
    }

    private fun observe() {
        //观察loading值决定是否加载loading框
        viewModel.isLoading.observe(this, Observer {
            if (it) {
                showLoading()
            } else {
                dismissAllLoading()
            }
        })
        //观察收藏数据变化
        viewModel.collectLiveData.observe(this, Observer {
            mCollectAdapter.updateData(it)
        })
    }
}