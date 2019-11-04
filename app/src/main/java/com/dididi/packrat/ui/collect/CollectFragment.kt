package com.dididi.packrat.ui.collect

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dididi.packrat.PackRatApp
import com.dididi.packrat.R
import com.dididi.packrat.data.CollectRepository
import com.dididi.packrat.data.local.PackRatDatabase
import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.data.model.collect.CollectContentType
import com.dididi.packrat.data.net.PackRatNetUtil
import com.dididi.packrat.ui.BaseFragment
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
        val layoutManager = LinearLayoutManager(activity!!,LinearLayoutManager.VERTICAL,false)
        fragmentCollectContentRv.layoutManager = layoutManager
    }

    override fun bindChildView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mCollectAdapter = CollectListAdapter(activity!!)
        fragmentCollectContentRv.adapter = mCollectAdapter
        observe()
        viewModel.setCollects(arrayListOf(Collect(1,"dididi","text",CollectContentType.WEB.value,"www.baidu.com",22L),Collect(2,"dididi","test",CollectContentType.WEB.value,"www.jianshu.com",22L)))
    }

    private fun observe() {
        viewModel.dataChangeLiveData.observe(this, Observer {
            mCollectAdapter.collectList = viewModel.dataList
            mCollectAdapter.notifyDataSetChanged()
        })

        viewModel.isLoading.observe(this, Observer {

        })
        if (viewModel.dataList.isEmpty()) {
            viewModel.getCollects()
        }
    }
}