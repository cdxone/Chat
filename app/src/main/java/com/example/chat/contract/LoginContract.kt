package com.example.chat.contract

interface LoginContract {

    interface Presenter : BasePresenter {
        fun login(name: String, psd: String)
    }

    interface View {
        fun onNameError()
        fun onPsdError()
        fun onStartLogin()
        fun onLoggedInSuccess()
        fun onLoggedInFailed()
    }
}