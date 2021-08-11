package com.example.chat.weight

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.chat.R

/**
 * ❤联系人界面每个条目的布局
 * 1、一般情况下使用2个参数的布局
 * 2、attrs属性默认为null
 */
class ContractListItem(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    // ❤初始化布局放在init块里面
    init {
        View.inflate(context, R.layout.contract_list_item,this)
    }

}