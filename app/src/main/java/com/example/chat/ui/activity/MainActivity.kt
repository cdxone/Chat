package com.example.chat.ui.activity

import com.example.chat.R
import com.example.chat.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun init() {
        super.init()
        bnve.setOnNavigationItemSelectedListener {
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.fl_container, FragmentFactory.instance.getFragment(it.itemId)!!)
            beginTransaction.commit()
            true
        }
    }
}