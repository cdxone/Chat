package com.example.chat.contract

interface AddFriendContract {

    interface Presenter : BasePresenter {
        fun addFriend(name: String, reason: String)
    }

    interface View {
        fun addFriendSuccess()
        fun addFriendFailed()
    }
}