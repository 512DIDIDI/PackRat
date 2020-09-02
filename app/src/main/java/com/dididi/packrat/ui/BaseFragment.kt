package com.dididi.packrat.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 23/10/2019
 * @describe
 */

abstract class BaseFragment : Fragment() {

    /**
     * 提供AppCompatActivity实例
     */
    protected lateinit var mActivity: RootActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is RootActivity) {
            mActivity = context
        }
    }

    /**
     * 设置布局，可传入id或view
     */
    abstract fun setLayout(): Any

    /**
     * 业务逻辑
     */
    abstract fun doBusiness()

    /**
     * 是否拦截返回键 默认不拦截
     */
    open fun onBackPressed() = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = when (setLayout()) {
        is Int -> inflater.inflate(setLayout() as Int, container, false)
        is View -> setLayout() as View
        else -> throw TypeCastException("setLayout()方法返回类型错误，需传入resId或view类型")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doBusiness()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mActivity.currentFragment = this
    }

}