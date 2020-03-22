package com.dididi.packrat.ui.login

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.dididi.packrat.R
import com.dididi.packrat.ui.BaseMainNavFragment
import com.dididi.packrat.utils.*
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.layout_forget_password.*
import kotlinx.android.synthetic.main.layout_login.*
import kotlinx.android.synthetic.main.layout_reset_password.*
import kotlinx.android.synthetic.main.layout_signup.*


/**
 * @author dididi(yechao)
 * @since 10/12/2019
 * @describe 登录页
 */

class LoginFragment : BaseMainNavFragment(), View.OnClickListener {

    companion object {
        const val minDistance = 100
        const val minVelocity = 10
        const val minInterval = 500
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun setLayout() = R.layout.fragment_login

    override fun bindView(savedInstanceState: Bundle?, rootView: View) {
        observe()
        clickEvent()
        setImmersionBar()
    }

    override fun onBackPressed() = false

    /**
     * 设置状态栏导航栏状态
     */
    private fun setImmersionBar() {
        immersionBar {
            reset()
            barColor(R.color.backgroundColorGray)
            statusBarDarkFont(true)
            navigationBarDarkIcon(true)
            fitsSystemWindows(true)
        }
    }

    private fun clickEvent() {
        //滚动监听
        val gestureDetector = GestureDetector(activity!!, EnterLoginGestureListener())
        fragmentLoginInitLayout.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }
        fragmentLoginSignInLayoutSignUp.setOnClickListener(this)
        fragmentLoginSignInLayoutSignIn.setOnClickListener(this)
        fragmentLoginSignInLayoutForgetPassword.setOnClickListener(this)
        fragmentLoginSignUpLayoutBack.setOnClickListener(this)
        fragmentLoginSignUpLayoutSignUp.setOnClickListener(this)
        fragmentLoginForgetPasswordLayoutBack.setOnClickListener(this)
        fragmentLoginForgetPasswordLayoutSendVerify.setOnClickListener(this)
        fragmentLoginForgetPasswordLayoutNext.setOnClickListener(this)
        fragmentLoginResetPasswordLayoutBack.setOnClickListener(this)
        fragmentLoginResetPasswordLayoutLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fragmentLoginSignInLayoutSignUp -> {
                getSignUpAnimation(fragmentLoginSignInLayout)
            }
            R.id.fragmentLoginSignInLayoutSignIn -> {
                //调用登录
                viewModel.login(
                    fragmentLoginSignInLayoutAccount.text.toString(),
                    fragmentLoginSignInLayoutPassword.text.toString()
                )
            }
            R.id.fragmentLoginSignInLayoutForgetPassword -> {
                getForgetPasswordAnimation(fragmentLoginSignInLayout)
            }
            R.id.fragmentLoginSignUpLayoutBack -> {
                getSignInAnimation(fragmentLoginSignUpLayout)
            }
            R.id.fragmentLoginSignUpLayoutSignUp -> {
                //调用注册
                viewModel.register(
                    fragmentLoginSignUpLayoutAccount.text.toString(),
                    fragmentLoginSignUpLayoutPassword.text.toString(),
                    fragmentLoginSignUpLayoutReenterPassword.text.toString()
                )
            }
            R.id.fragmentLoginForgetPasswordLayoutBack -> {
                getSignInAnimation(fragmentLoginForgetPasswordLayout)
            }
            R.id.fragmentLoginForgetPasswordLayoutSendVerify -> {
            }
            R.id.fragmentLoginForgetPasswordLayoutNext -> {
                getResetPasswordAnimation(fragmentLoginForgetPasswordLayout)
            }
            R.id.fragmentLoginResetPasswordLayoutBack -> {
                getForgetPasswordAnimation(fragmentLoginResetPasswordLayout)
            }
            R.id.fragmentLoginResetPasswordLayoutLogin -> {
                getSignInAnimation(fragmentLoginResetPasswordLayout)
            }
            else -> {
            }
        }
    }

    /**
     * 监听数据变化
     */
    private fun observe() {
        //登录监听
        viewModel.loginLiveData.observe(this, Observer {
            if (it.errorCode != 0) {
                toast(it.errorMsg)
            } else {
                Navigation.findNavController(fragmentLoginSignInLayoutSignIn)
                    .navigate(R.id.action_login_to_home)

            }
        })
        //注册监听
        viewModel.registerLiveData.observe(this, Observer {
            if (it.errorCode != 0) {
                toast(it.errorMsg)
            } else {
                toast("注册成功，返回登录页登录")
                getSignInAnimation(fragmentLoginSignUpLayout)
            }
        })
        //loading窗口监听
        viewModel.isLoading.observe(this, Observer {
            if (it) {
                showLoading()
            } else {
                dismissAllLoading()
            }
        })
    }


    /**
     * 入场动画手势
     */
    inner class EnterLoginGestureListener : GestureDetector.SimpleOnGestureListener() {

        private var lastTime = System.currentTimeMillis()

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            //滚动一定距离 间隔一定时间 速度达到一定 并且控件未被隐藏时出现动画
            if ((e1!!.y - e2!!.y > minDistance) &&
                distanceY > minVelocity &&
                fragmentLoginEnterSign.visibility == View.VISIBLE &&
                System.currentTimeMillis() - lastTime > minInterval
            ) {
                val interval = System.currentTimeMillis() - lastTime
                //滚动速度
                val velocity = distanceY / interval * 1000
                //力道 根据力道决定回弹效果
                val strength = velocity / 50
                enterSign(strength)
                lastTime = System.currentTimeMillis()
            }
            return false
        }

        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }
    }

    /**
     * 从启动页进入登录页动画
     */
    private fun enterSign(strength: Float) {
        fragmentLoginEnterSign.setAlphaAnimation(1f, 0f, duration = 500, isEnable = false)
        //logo与app名字
        fragmentLoginLogo.setTranslationAnimation(
            Translation.TranslationY,
            fragmentLoginLogo.translationY,
            fragmentLoginFakeLogo.y - fragmentLoginLogo.y,
            duration = 600, strength = strength
        )
        fragmentLoginAppName.setTranslationAnimation(
            Translation.TranslationY,
            fragmentLoginAppName.translationY,
            fragmentLoginFakeAppName.y - fragmentLoginAppName.y,
            duration = 600, strength = strength
        )
        //登录背景页面
        setMoveDelayAnimation(
            Translation.TranslationY,
            activity!!.getScreeSize().y.toFloat(),
            startAlpha = 0f, endAlpha = 1f, isEnable = true, strength = strength,
            firstDelay = 0, eachDelay = 100, eachDuration = 600,
            views = *arrayOf(fragmentLoginSignLayout, fragmentLoginDididi)
        )
        //登录信息控件
        setMoveDelayAnimation(
            Translation.TranslationX,
            activity!!.getScreeSize().x.toFloat(),
            startAlpha = 0f, endAlpha = 1f, isEnable = true,
            firstDelay = 500, eachDelay = 100, eachDuration = 600,
            views = *arrayOf(
                fragmentLoginSignInLayoutLogin,
                fragmentLoginSignInLayoutAccount,
                fragmentLoginSignInLayoutPassword,
                fragmentLoginSignInLayoutSignIn,
                fragmentLoginSignInLayoutSignUp,
                fragmentLoginSignInLayoutForgetPassword
            )
        )
    }

    private fun getSignUpAnimation(frontView: View) {
        context?.setFlipAnimation(
            backgroundView = fragmentLoginSignLayout,
            frontView = frontView,
            behindView = fragmentLoginSignUpLayout
        )
    }

    private fun getSignInAnimation(frontView: View) {
        context?.setFlipAnimation(
            backgroundView = fragmentLoginSignLayout,
            frontView = frontView,
            behindView = fragmentLoginSignInLayout
        )
    }

    private fun getForgetPasswordAnimation(frontView: View) {
        context?.setFlipAnimation(
            backgroundView = fragmentLoginSignLayout,
            frontView = frontView,
            behindView = fragmentLoginForgetPasswordLayout
        )
    }

    private fun getResetPasswordAnimation(frontView: View) {
        context?.setFlipAnimation(
            backgroundView = fragmentLoginSignLayout,
            frontView = frontView,
            behindView = fragmentLoginResetPasswordLayout
        )
    }
}