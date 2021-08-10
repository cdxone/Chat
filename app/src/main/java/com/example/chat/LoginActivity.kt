package com.example.chat

import android.view.KeyEvent
import android.widget.TextView
import com.example.chat.contract.LoginContract
import com.example.chat.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity(), LoginContract.View {

    val presenter = LoginPresenter(this)

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun init() {
        super.init()
        btn_login.setOnClickListener {
            login()
        }
        // 监听键盘的登录按钮
        et_psd.setOnEditorActionListener { v, actionId, event ->
            login()
            true
        }
    }

    private fun login() {
        presenter.login(et_name.text.toString(), et_psd.text.toString())
    }

    override fun onNameError() {
        // 使用了这个插件kotlin-android-extensions，可以让控件的设置更加容易
        et_name.error = "用户名错误"
    }

    override fun onPsdError() {
        et_psd.error = "密码错误"
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