package com.example.chat.ui.activity

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    private val progressDialog by lazy {
        ProgressDialog(this)
    }

    // 软键盘
    private val inputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

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

    fun showProgress(msg: String) {
        progressDialog.setMessage(msg)
        progressDialog.show()
    }

    fun dismissProgress() {
        progressDialog.dismiss()
    }

    /**
     * 隐藏软键盘
     * 因为很多页面都有这个需求
     */
    fun hideSoftKeyBoard() {
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}