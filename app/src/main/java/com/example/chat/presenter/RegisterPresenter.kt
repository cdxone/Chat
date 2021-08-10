package com.example.chat.presenter

import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.example.chat.contract.RegisterContract
import com.example.chat.extentions.isValidName
import com.example.chat.extentions.isValidPsd
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


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
            registerBmob(name, psd);
        } catch (e: Exception) {
            // 注册失败
            view.onRegisterFailed()
            return
        }

        // 注册成功
        view.onRegisterSuccess()

    }

    private fun registerBmob(name: String, psd: String) {
        // 注册Bomb
        val user = BmobUser()
        user.username = name
        user.setPassword(psd)
        user.signUp(object : SaveListener<BmobUser>() {
            override fun done(t: BmobUser?, e: BmobException?) {
                if (e == null) {
                    // 注册到环信
                    registerEasyMob(name, psd);
                    //注册失败会抛出HyphenateException
                    //EMClient.getInstance().createAccount(username, pwd);//同步方法
                } else {
                    view.onRegisterFailed()
                }
            }

        })
    }

    /**
     * 注册到环信
     */
    private fun registerEasyMob(name: String, psd: String) {
        //注册失败会抛出HyphenateException
        // 注册环信
        // 将代码段放在子线程中执行
        doAsync {
            try {
                EMClient.getInstance().createAccount(name, psd) //同步方法
                // 注冊成功
                // 切换到主线程中运行
                uiThread {
                    view.onRegisterSuccess()
                }
            } catch (e: java.lang.Exception) {
                // 注冊失敗
                // 切换到主线程中运行
                uiThread {
                    view.onRegisterFailed()
                }
            }
        }
    }

}