package com.dididi.packrat.ui.collect

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dididi.packrat.R
import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.ui.BaseFragment
import com.dididi.uiextlib.ext.dismissAllLoading
import com.dididi.uiextlib.ext.showLoading
import kotlinx.android.synthetic.main.fragment_collect.*


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 23/10/2019
 * @describe 收藏list界面
 */

class CollectFragment : BaseFragment() {

    private lateinit var mCollectAdapter: CollectListAdapter

    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(CollectViewModel::class.java)
    }

    override fun setLayout() = R.layout.fragment_collect

    override fun bindView(savedInstanceState: Bundle?, rootView: View) {
        val layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        fragmentCollectContentRv.layoutManager = layoutManager
        mCollectAdapter = CollectListAdapter(requireActivity())
        fragmentCollectContentRv.adapter = mCollectAdapter
    }

    override fun onBackPressed() = false

    override fun doBusiness() {
        observe()
        //todo:这里得重新考虑下。应该怎么赋值数据。这里会出bug，因为onViewCreated在navigationView切换过程中只初始化一次，所以数据会保存不了。
        viewModel.getCollects {
            mCollectAdapter.collectList = it as MutableList<Collect>
        }
        clickEvent()
    }

    private fun clickEvent() {
//        fragmentCollectFab.onSingleClick(500L) {
//            mainNavController.navigate(R.id.action_home_to_addCollect)
//        }
    }

    private fun observe() {
        //观察loading值决定是否加载loading框
        viewModel.isLoading.observe(this, {
            if (it) {
                showLoading()
            } else {
                dismissAllLoading()
            }
        })
        //观察收藏数据变化
        viewModel.collectLiveData.observe(this, {
            mCollectAdapter.insertCollect(it)
        })
    }
}