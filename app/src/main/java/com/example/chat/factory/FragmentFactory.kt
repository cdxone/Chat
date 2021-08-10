package com.example.chat.factory

import androidx.fragment.app.Fragment
import com.example.chat.R
import com.example.chat.ui.fragment.ContractFragment
import com.example.chat.ui.fragment.ConversationFragment
import com.example.chat.ui.fragment.DynamicFragment

/**
 * 单例的实现
 * Fragment的工厂实现
 */
class FragmentFactory private constructor() {

    companion object {
        val instance = FragmentFactory()
    }

    private val conversation by lazy {
        ConversationFragment()
    }

    private val contract by lazy {
        ContractFragment()
    }

    private val dynamic by lazy {
        DynamicFragment()
    }

    fun getFragment(id: Int): Fragment? {
        return when (id) {
            R.id.menu_conversation -> conversation
            R.id.menu_contract -> contract
            R.id.menu_dynamic -> dynamic
            else -> null
        }
    }
}