package com.example.chat

import com.example.chat.contract.RegisterContract
import com.example.chat.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_login.et_name
import kotlinx.android.synthetic.main.activity_login.et_psd
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseActivity(), RegisterContract.View {

    private val presenter = RegisterPresenter(this)

    override fun getLayoutId(): Int = R.layout.activity_register

    override fun init() {
        super.init()
        btn_register.setOnClickListener{
            hideSoftKeyBoard()
            presenter.register(et_name.text.toString(), et_psd.text.toString(), et_re_input_psd.text.toString())
        }
    }

    override fun onNameError() {
        et_name.error = "用户名错误"
    }

    override fun onPsdError() {
        et_psd.error = "用户名错误"
    }

    override fun onReInputPsdError() {
        et_re_input_psd.error = "再次输入用户名错误"
    }

    override fun onStartRegister() {
        showProgress("开始注册")
    }

    override fun onRegisterSuccess() {
        dismissProgress()
        toast("注册成功")
    }

    override fun onRegisterFailed() {
        dismissProgress()
        toast("注册失败")
    }

}