package com.example.chat.ui.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat.R
import com.example.chat.adapter.ContractListAdapter
import com.example.chat.contract.ContactContract
import com.example.chat.presenter.ContactPresenter
import com.example.chat.ui.activity.ChatActivity
import kotlinx.android.synthetic.main.fragment_contract.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class ContractFragment : BaseFragment(), ContactContract.View {

    override fun getLayoutId(): Int = R.layout.fragment_contract

    private val presenter = ContactPresenter(this)

    private val list = mutableListOf<String>()
    private val contractListAdapter = ContractListAdapter(list)

    override fun init() {
        super.init()
        // 初始化SwipeRefreshLayout
        sfl.apply {
            setColorSchemeColors(Color.RED)
            isRefreshing = true
            setOnRefreshListener {
                presenter.loadContacts()
            }
        }
        // 初始化RecyclerView
        rv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = contractListAdapter
        }

        contractListAdapter.setOnItemClickListener { adapter, view, position ->
            val name = list[position];
            context?.startActivity<ChatActivity>("username" to name)
        }
        contractListAdapter.setOnItemLongClickListener { adapter, view, position ->
            val name = list[position];
            val dialog = AlertDialog.Builder(context)
                    .setTitle("是否删除")
                    .setPositiveButton("是的") { dialog, _which ->
                        dialog.dismiss()
                        presenter.deleteContact(name)
                    }
                    .setNegativeButton("取消") { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            true
        }

        // 加载数据
        presenter.loadContacts()
    }

    override fun onLoaContactsSuccess(usernames: MutableList<String>) {
        list.clear()
        list.addAll(usernames)
        sfl.isRefreshing = false
        rv.adapter?.notifyDataSetChanged()
    }

    override fun onLoadContactsFailed() {
        sfl.isRefreshing = false
        context?.toast("加载失败")
    }

    override fun onDeleteContactSuccess() {
        context?.toast("删除成功")
        presenter.loadContacts()
    }

    override fun onDeleteContactFailed() {
        context?.toast("删除失败")
    }

}