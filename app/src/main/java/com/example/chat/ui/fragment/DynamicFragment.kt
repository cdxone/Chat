package com.example.chat.ui.fragment

import com.example.chat.R
import com.example.chat.ui.activity.LoginActivity
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_dynamic.*
import org.jetbrains.anko.custom.onUiThread
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class DynamicFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_dynamic

    override fun init() {
        super.init()
        btn_logout.setOnClickListener {
            EMClient.getInstance().logout(true, object : EMCallBack {
                override fun onSuccess() {
                    context?.startActivity<LoginActivity>()
                    activity?.finish()
                    // 由于是在子线程，更新UI需要在主线程中进行操作
                    context?.runOnUiThread {
                        toast("退出成功")
                    }
                }

                override fun onProgress(progress: Int, status: String) {
                }

                override fun onError(code: Int, message: String) {
                    // 由于是在子线程，更新UI需要在主线程中进行操作
                    context?.runOnUiThread {
                        toast("退出失败")
                    }
                }
            })
        }
    }
}