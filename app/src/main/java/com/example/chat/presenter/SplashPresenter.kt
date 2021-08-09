package com.example.chat.presenter

import com.example.chat.contract.SplashContract
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions

class SplashPresenter(private val view: SplashContract.View) : SplashContract.Presenter {

    override fun checkLoginState() {
        if (isLoggedIn()) view.onLoggedIn() else view.onNotLoggedIn()
    }

    private fun isLoggedIn(): Boolean {
        return EMClient.getInstance().isConnected && EMClient.getInstance().isLoggedInBefore
    }

}