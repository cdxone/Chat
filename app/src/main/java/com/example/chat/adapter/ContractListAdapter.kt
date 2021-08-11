package com.example.chat.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.chat.R

class ContractListAdapter(data: MutableList<String>? = null) :
        BaseQuickAdapter<String, BaseViewHolder>(R.layout.contract_list_item, data) {


    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setImageResource(R.id.iv_head, android.R.drawable.ic_dialog_map);
        holder.setText(R.id.tv_name, item)
    }

}