package com.example.chat.presenter

import android.view.View
import com.example.chat.contract.AddFriendContract
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import java.lang.Exception

/**
 * 这是一个控制中心,持有一个View的引用，然后调用Model来给View更新
 */
class AddFriendPresenter(val view: AddFriendContract.View) : AddFriendContract.Presenter {

    override fun addFriend(name: String, reason: String) {
        doAsync {
            try {
                // 添加好友
                EMClient.getInstance().contactManager().addContact(name, reason);
                // 添加成功
                uiThread {
                    view.addFriendSuccess()
                }
            } catch (e: Exception) {
                // 添加失败
                uiThread {
                    view.addFriendFailed()
                }
            }
        }
    }
}