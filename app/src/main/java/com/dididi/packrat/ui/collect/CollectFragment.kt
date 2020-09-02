package com.dididi.packrat.ui.collect

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.dididi.packrat.R
import com.dididi.packrat.data.model.collect.Collect
import com.dididi.packrat.ui.BaseFragment
import com.dididi.packrat.ui.getMainNav
import com.dididi.uiextlib.ext.dismissAllLoading
import com.dididi.uiextlib.ext.showLoading
import com.dididi.uiextlib.ext.showPopupMenu
import com.dididi.uiextlib.ext.showSnackBar
import kotlinx.android.synthetic.main.fragment_collect.*


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 23/10/2019
 * @describe 收藏list界面
 */

class CollectFragment : BaseFragment(),OnItemChildClickListener {

    private val adapter = CollectAdapter(null)

    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(CollectViewModel::class.java)
    }

    override fun setLayout() = R.layout.fragment_collect

    override fun onBackPressed() = false

    override fun doBusiness() {
        val layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        fragmentCollectContentRv.adapter = adapter
        fragmentCollectContentRv.layoutManager = layoutManager
        //todo:这里得重新考虑下。应该怎么赋值数据。这里会出bug，因为onViewCreated在navigationView切换过程中只初始化一次，所以数据会保存不了。
        viewModel.getCollects {
        }
        observe()
        clickEvent()
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
            adapter.addData(it)
        })
    }

    private fun clickEvent() {
        adapter.addChildClickViewIds(R.id.itemCollectTextCv,R.id.itemCollectTextMore,R.id.itemCollectTextTitle)
        adapter.setOnItemChildClickListener(this)
        fragmentCollectFabText.setOnClickListener {
            getMainNav(requireActivity()).navigate(R.id.action_home_to_addCollect)
        }
//        fragmentCollectFab.onSingleClick(500L) {
//            mainNavController.navigate(R.id.action_home_to_addCollect)
//        }
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        when(view.id){
            R.id.itemCollectTextCv -> {
                val item = adapter.getItem(position) as Collect
                showSnackBar("进入${item.content}页面")
            }
            R.id.itemCollectTextMore -> {
                showPopupMenu(adapter.getViewByPosition(position,view.id)!!,R.menu.menu_collect_more)
            }
            R.id.itemCollectTextTitle -> {
                showSnackBar("点击标题")
            }
        }
    }

}