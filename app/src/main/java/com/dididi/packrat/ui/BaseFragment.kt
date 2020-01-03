package com.dididi.packrat.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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
    protected lateinit var mActivity:AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AppCompatActivity){
            mActivity = context
        }
    }

    /**
     * 设置布局，可传入id或view
     */
    abstract fun setLayout(): Any

    /**
     * 绑定控件
     */
    abstract fun bindView(savedInstanceState: Bundle?, rootView: View)

    /**
     * 绑定子控件
     */
    abstract fun bindChildView(savedInstanceState: Bundle?, rootView: View)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = when (setLayout()) {
            is Int -> inflater.inflate(setLayout() as Int, container, false)
            is View -> setLayout() as View
            else -> throw TypeCastException("setLayout()方法返回类型错误，需传入resId或view类型")
        }
        bindChildView(savedInstanceState, rootView)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //需在onViewCreated中定义，否则kotlinx在fragment中无法使用
        bindView(savedInstanceState, view)
    }
}