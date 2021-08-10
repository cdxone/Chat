package com.example.chat.presenter

import com.example.chat.contract.LoginContract
import com.example.chat.extentions.isValidName
import com.example.chat.extentions.isValidPsd

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {
    override fun login(name: String, psd: String) {
        // 检测用户名是否合法
        if (!name.isValidName()) {
            view.onNameError()
            return
        }

        // 检测密码是否合法
        if (!psd.isValidPsd()) {
            view.onPsdError()
            return
        }

        loginEaseMob(name,psd)
    }

    private fun loginEaseMob(name: String, psd: String) {

    }
}