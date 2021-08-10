package com.example.chat.presenter

import android.R.attr.password
import android.util.Log
import com.example.chat.contract.LoginContract
import com.example.chat.extentions.isValidName
import com.example.chat.extentions.isValidPsd
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient

/**
 * Presenter层中，需要调用Model层，然后控制View层的一个显示与否
 */
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

        loginEaseMob(name, psd)
    }

    /**
     * 登录服务器
     */
    private fun loginEaseMob(name: String, psd: String) {
        // ❤ 这个地方就是Modle的实现
        EMClient.getInstance().login(name, psd, object : EMCallBack {
            // 在子线程中回调的
            override fun onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups()
                EMClient.getInstance().chatManager().loadAllConversations()
                Log.e("main", "登录聊天服务器成功！")

                // ❤在主线程中通知View层
                uiThread { view.onLoggedInSuccess() }
            }

            override fun onProgress(progress: Int, status: String) {}

            override fun onError(code: Int, message: String) {
                Log.e("main", "登录聊天服务器失败！")
                uiThread { view.onLoggedInFailed() }
            }
        })
    }
}