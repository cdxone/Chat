package com.example.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()
    }

    open fun init() {
        // 初始化公共的功能，比如摇一摇功能
        // 子类也可以复写该方法
    }

    // 获得布局的Id
    abstract fun getLayoutId(): Int

}