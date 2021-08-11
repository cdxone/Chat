package com.example.chat.contract

class ContactContract {

    interface Presenter : BasePresenter {
        // 加载联系人
        fun loadContacts()
        // 删除联系人
        fun deleteContact(name: String)
    }

    interface View {
        fun onLoaContactsSuccess(usernames: MutableList<String>)
        fun onLoadContactsFailed()
        fun onDeleteContactSuccess()
        fun onDeleteContactFailed()
    }

}