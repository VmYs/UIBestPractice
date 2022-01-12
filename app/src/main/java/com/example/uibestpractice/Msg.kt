package com.example.uibestpractice

class Msg(val content: String, val type: Int) {
    //只有在单例类、companion object或顶层方法中才可以使用const关键字定义常量
    companion object {
        const val TYPE_RECEIVED = 0
        const val TYPE_SENT = 1
    }
}