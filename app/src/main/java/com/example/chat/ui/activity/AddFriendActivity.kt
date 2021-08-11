package com.example.chat.ui.activity

import com.example.chat.R
import com.example.chat.contract.AddFriendContract
import com.example.chat.presenter.AddFriendPresenter
import kotlinx.android.synthetic.main.activity_add_friend.*
import org.jetbrains.anko.toast

class AddFriendActivity : BaseActivity(), AddFriendContract.View {

    override fun getLayoutId(): Int = R.layout.activity_add_friend

    private val presenter = AddFriendPresenter(this)

    override fun init() {
        super.init()
        btn_add_friend.setOnClickListener {
            presenter.addFriend(et_name.text.toString(), et_reason.text.toString())
        }
    }

    override fun addFriendSuccess() {
        toast("添加成功")
    }

    override fun addFriendFailed() {
        toast("添加失败")
    }

}