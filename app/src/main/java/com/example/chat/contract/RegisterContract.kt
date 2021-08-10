package com.example.chat.contract

interface RegisterContract {

    interface Presenter : BasePresenter {
        fun register(name: String, psd: String, rePsd: String)
    }

    interface View {
        fun onNameError()
        fun onPsdError()
        fun onReInputPsdError()
        fun onStartRegister()
        fun onRegisterSuccess()
        fun onRegisterFailed()
    }
}