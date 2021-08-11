package com.example.chat.contract

class ContactContract {

    interface Presenter : BasePresenter {
        fun loadContacts()
    }

    interface View {
        fun onLoaContactsSuccess(usernames: MutableList<String>)
        fun onLoadContactsFailed()
    }

}