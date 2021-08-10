package com.example.chat.ui.activity

import android.os.Handler
import android.os.Looper
import com.example.chat.R
import com.example.chat.contract.SplashContract
import com.example.chat.presenter.SplashPresenter
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity(), SplashContract.View {

    // 初始化presenter
    private val presenter = SplashPresenter(this)

    // 常量一般放在伴生对象里面
    companion object {
        const val DELAY = 2000L
    }

    private val handler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun init() {
        super.init()
        presenter.checkLoginState()
    }

    // 延迟2秒，跳转到登录界面
    override fun onNotLoggedIn() {
        handler.postDelayed({
            startActivity<LoginActivity>()
            finish()
        }, DELAY)
    }

    // 登录到主界面
    override fun onLoggedIn() {
        startActivity<MainActivity>()
        finish()
    }

}