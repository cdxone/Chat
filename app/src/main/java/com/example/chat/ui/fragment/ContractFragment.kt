package com.example.chat.ui.fragment

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat.R
import com.example.chat.adapter.ContractListAdapter
import com.example.chat.contract.ContactContract
import com.example.chat.presenter.ContactPresenter
import kotlinx.android.synthetic.main.fragment_contract.*
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

        // 加载数据
        presenter.loadContacts()
    }

    override fun onLoaContactsSuccess(usernames: MutableList<String>) {
        sfl.isRefreshing = false
        list.addAll(usernames)
        rv.adapter?.notifyDataSetChanged()
    }

    override fun onLoadContactsFailed() {
        sfl.isRefreshing = false
        context?.toast("加载失败")
    }

}