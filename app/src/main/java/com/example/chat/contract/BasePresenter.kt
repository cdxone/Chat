package com.example.chat.contract

import android.os.Handler
import android.os.Looper


/**
 * 用来做Presenter的扩展
 */
interface BasePresenter {

    // 伴生对象，相当于是静态的
    companion object {
        val handler by lazy {
            Handler(Looper.getMainLooper())
        }
    }

    /**
     * 用来在子线程和主线程中进行切换
     * @param f 这是个函数，传入一个函数，然后在主线程中调用
     */
    fun uiThread(f: () -> Unit) {
        // 因为handler是主线程的，所以可以进行主线程切换
        handler.post {
            f()
        }
    }
}