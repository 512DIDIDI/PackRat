package com.dididi.packrat.presentation.widget

import android.content.Context
import android.text.Layout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.dididi.packrat.R


/**
 * @author dididi(叶超)
 * @email yc512yc@163.com
 * @since 11/04/2020
 * @describe 带有icon text 的组合控件
 */
class WithIconTextView : LinearLayout {
    private lateinit var mIcon: ImageView
    private lateinit var mText: TextView

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initView(context)
        initTypedArray(context, attrs!!)
    }

    fun setIcon(@DrawableRes iconId: Int) {
        mIcon.setImageResource(iconId)
    }

    fun setText(text:CharSequence){
        mText.text = text
    }

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.view_with_icon_text, this, true)
        mText = findViewById(R.id.viewWithIconTextTv)
        mIcon = findViewById(R.id.viewWithIconTextIv)
    }

    private fun initTypedArray(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.WithIconTextView)
        val text = typedArray.getText(R.styleable.WithIconTextView_title)
        val icon = typedArray.getResourceId(R.styleable.WithIconTextView_icon, 0)
        typedArray.recycle()
        mIcon.setImageResource(icon)
        mText.text = text
    }
}