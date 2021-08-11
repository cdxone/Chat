package com.example.chat.presenter

import com.example.chat.contract.ContactContract
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.doAsync
import java.lang.Exception


class ContactPresenter(val view: ContactContract.View) : ContactContract.Presenter {

    override fun loadContacts() {
        getAllContactsFromServer();
    }

    override fun deleteContact(name: String) {
        doAsync {
            //1、让代码可以在子线程中运行
            try {
                // 这个是一个同步的方法
                EMClient.getInstance().contactManager().deleteContact(name);
                // 加载成功
                //2、切换到主线程中运行
                uiThread {
                    view.onDeleteContactSuccess()
                }
            } catch (e: Exception) {
                // 加载失败
                //2、切换到主线程中运行
                uiThread {
                    view.onDeleteContactFailed()
                }
            }
        }
    }

    /**
     * 获取好友列表
     * 这个是Model层
     */
    private fun getAllContactsFromServer() {
        doAsync {
            //1、让代码可以在子线程中运行
            try {
                // 这个是一个同步的方法
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                // 加载成功
                //2、切换到主线程中运行
                uiThread {
                    view.onLoaContactsSuccess(usernames)
                }
            } catch (e: Exception) {
                // 加载失败
                //2、切换到主线程中运行
                uiThread {
                    view.onLoadContactsFailed()
                }
            }
        }
    }
}