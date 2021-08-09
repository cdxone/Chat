package com.example.chat.presenter

import com.example.chat.contract.SplashContract

class SplashPresenter(private val view: SplashContract.View) : SplashContract.Presenter {

    override fun checkLoginState() {
        if (isLoggedIn()) view.onLoggedIn() else view.onNotLoggedIn()
    }

    private fun isLoggedIn(): Boolean = false

}