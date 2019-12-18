package com.dididi.packrat.utils

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import android.view.animation.OvershootInterpolator


/**
 * @author dididi(yechao)
 * @since 16/12/2019
 * @describe 动画拓展类
 */

enum class Rotation(val value: String) {
    /**
     * 水平旋转
     */
    RotationX("rotationX"),
    /**
     * 垂直旋转
     */
    RotationY("rotationY")
}

enum class Translation(val value: String) {
    /**
     * 水平位移
     */
    TranslationX("translationX"),
    /**
     * 垂直位移
     */
    TranslationY("translationY")
}

/**
 * 获取属性动画
 * @param view 目标
 * @param propertyName 动作
 * @param start 动画开始
 * @param end 动画结束
 * @param delay 延迟
 * @param duration 时长
 * @return 返回属性动画对象
 */
private fun getObjectAnimator(
    view: View,
    propertyName: String,
    start: Float,
    end: Float,
    delay: Long,
    duration: Long
): ObjectAnimator = ObjectAnimator.ofFloat(view, propertyName, start, end).apply {
    startDelay = delay
    setDuration(duration)
}

/**
 * view的位移动画
 * [Translation]
 * @param isOvershoot 是否需要反弹效果
 */
fun View.setTranslationAnimation(
    translation: Translation,
    start: Float,
    distance: Float,
    delay: Long = 0,
    duration: Long,
    isOvershoot: Boolean = true,
    strength:Float = 1.5f
): Unit = getObjectAnimator(this, translation.value, start, distance, delay, duration).apply {
    if (isOvershoot) {
        interpolator = OvershootInterpolator(strength)
    }
}.start()

/**
 * view的透明动画，
 * @param isEnable 决定控件alpha为0时是否仍有效
 */
fun View.setAlphaAnimation(
    start: Float, end: Float, delay: Long = 0, duration: Long, isEnable: Boolean
): Unit = getObjectAnimator(this, "alpha", start, end, delay, duration).apply {
    addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
        }

        override fun onAnimationEnd(animation: Animator?) {
            //如果无效，则隐藏时view invisible
            if (!isEnable) {
                this@setAlphaAnimation.visibility = View.INVISIBLE
            }
        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationStart(animation: Animator?) {
            //如果有效 则动画开始时，使其可见
            if (isEnable) {
                this@setAlphaAnimation.visibility = View.VISIBLE
            }
        }
    })
}.start()

/**
 * 设置旋转动画
 * [Rotation]
 * @param isOvershoot 是否需要回弹效果
 */
fun View.setRotateAnimation(
    rotation: Rotation,
    start: Float,
    end: Float,
    delay: Long = 0,
    duration: Long,
    isOvershoot: Boolean = true,
    strength:Float = 1.5f
): Unit = getObjectAnimator(this, rotation.value, start, end, delay, duration).apply {
    if (isOvershoot) {
        interpolator = OvershootInterpolator(strength)
    }
}.start()

/**
 * 翻转动画实现
 * @param backgroundView 背景(可空)
 * @param frontView 当前显示的view
 * @param behindView 需要显示的view
 */
fun Context.setFlipAnimation(
    rotation: Rotation = Rotation.RotationY,
    backgroundView: View?,
    frontView: View,
    behindView: View
) {
    setCameraDistance(backgroundView, frontView, behindView)
    //当前显示与背景旋转180并隐藏
    frontView.setAlphaAnimation(1f, 0f, 0, 500, false)
    backgroundView?.setRotateAnimation(rotation, 0f, 180f, 0, 1000, false)
    frontView.setRotateAnimation(rotation, 0f, 180f, 0, 1000, false)
    //需要显示的view 旋转-180度并显示
    behindView.setRotateAnimation(rotation, 0f, -180f, 0, 1000, false)
    behindView.setAlphaAnimation(0f, 1f, 500, 500, true)
}

/**
 * 延迟入场动画
 * @param startPos 位移开始位置
 * @param endPos 位移结束位置(当其为0时，则表明回到其默认位置)
 * @param startAlpha 初始透明度
 * @param endAlpha 末尾透明度
 * @param isEnable 控件是否有效
 * @param firstDelay 第一个控件的入场时间
 * @param eachDelay 剩下控件的入场时间
 * @param eachDuration 动画持续时长
 * @param views 控件
 */
fun setMoveDelayAnimation(
    translation: Translation,
    startPos: Float,
    endPos: Float = 0f,
    startAlpha: Float,
    endAlpha: Float,
    isEnable: Boolean,
    strength: Float = 1.5f,
    firstDelay: Long,
    eachDelay: Long,
    eachDuration: Long,
    vararg views: View
) {
    //获取位移结束位置
    fun getEndPos(view: View): Float =
        if (endPos == 0f) {
            if (translation == Translation.TranslationX) {
                view.translationX
            } else {
                view.translationY
            }
        } else {
            endPos
        }
    //第一个控件的动画
    views.first().run {
        setTranslationAnimation(
            translation,
            startPos,
            getEndPos(this),
            firstDelay,
            eachDuration,
            true,
            strength
        )
        setAlphaAnimation(startAlpha, endAlpha, firstDelay, eachDuration, isEnable)
    }
    //剩余控件的动画
    for (i in 1 until views.size) {
        val delay = eachDelay * i + firstDelay
        views[i].setTranslationAnimation(
            translation,
            startPos,
            getEndPos(views[i]),
            delay,
            eachDuration,
            true,
            strength
        )
        views[i].setAlphaAnimation(startAlpha, endAlpha, delay, eachDuration, isEnable)
    }
}

fun Context.setCameraDistance(vararg views: View?) {
    val distance = 16000
    val scale = this.resources.displayMetrics.density * distance
    for (view in views) {
        view?.cameraDistance = scale
    }
}
