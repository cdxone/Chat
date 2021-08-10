package com.example.chat.presenter

import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.example.chat.contract.RegisterContract
import com.example.chat.extentions.isValidName
import com.example.chat.extentions.isValidPsd


class RegisterPresenter(val view: RegisterContract.View) : RegisterContract.Presenter {

    override fun register(name: String, psd: String, rePsd: String) {
        // 首先验证用户名
        if (!name.isValidName()) {
            view.onNameError()
            return
        }

        // 然后验证密码
        if (!name.isValidPsd()) {
            view.onPsdError()
            return
        }

        // 然后再次验密码
        if (psd != rePsd) {
            view.onReInputPsdError()
            return
        }

        try {
            view.onStartRegister()
            // ❤ 这就相当于是Model层做的事情
            //注册失败会抛出HyphenateException
            // EMClient.getInstance().createAccount(name, psd);//同步方法
            registerBmob();
        } catch (e: Exception) {
            // 注册失败
            view.onRegisterFailed()
            return
        }

        // 注册成功
        view.onRegisterSuccess()

    }

    private fun registerBmob() {
        // 注册Bomb
        val user = BmobUser()
        user.username = "cdx" + System.currentTimeMillis()
        user.setPassword("123cdx")
        user.signUp(object : SaveListener<BmobUser>() {
            override fun done(t: BmobUser?, e: BmobException?) {
                if (e == null) {
                    // 注册到环信
                } else {
                    view.onRegisterFailed()
                }
            }

        })
    }

}