package com.example.chat

import com.example.chat.contract.LoginContract
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity(),LoginContract.View{
    override fun getLayoutId(): Int = R.layout.activity_login

    override fun onNameError() {
        // 使用了这个插件kotlin-android-extensions，可以让控件的设置更加容易
        tv_name.error = "用户名错误"
    }

    override fun onPsdError() {
        tv_psd.error = "密码错误"
    }

    override fun onStartLogin() {
        // 弹出进度条
        showProgress("隐藏进度条")
    }

    override fun onLoggedInSuccess() {
        dismissProgress()
        startActivity<MainActivity>()
        finish()
    }

    override fun onLoggedInFailed() {
        dismissProgress()
        toast("登录失败")
    }
}