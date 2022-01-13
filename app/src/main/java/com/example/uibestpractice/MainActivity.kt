package com.example.uibestpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uibestpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //private lateinit var binding: ActivityMainBinding

    private val msgList = ArrayList<Msg>()

    private var adapter : MsgAdapter ?= null
    private var bindingInit:ActivityMainBinding ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initMsg()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        adapter = MsgAdapter(msgList)
        binding.recyclerView.adapter = adapter
        binding.send.setOnClickListener(this)
        println( binding.inputText.toString())
        bindingInit = binding
    }

    override fun onClick(v: View?) {
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.recyclerView.layoutManager = layoutManager
//
//        binding.recyclerView.adapter = adapter
        when (v) {
            bindingInit?.send -> {
                val content = bindingInit?.inputText.toString()
                if (content.isNotEmpty()) {
                    val msg = Msg(content, Msg.TYPE_SENT)
                    msgList.add(msg)
                    adapter?.notifyItemInserted(msgList.size - 1) //当有新消息时，刷新RecyclerView中的显示
                    bindingInit?.recyclerView?.scrollToPosition(msgList.size - 1) //将RecyclerView定位到最后一行
                    bindingInit?.inputText?.setText("") //清空输入框中的内容
                }
            }
        }
    }

    private fun initMsg() {
        val msg1 = Msg("Hello guy.", Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 = Msg("Hello. Who is that?", Msg.TYPE_SENT)
        msgList.add(msg2)
        val msg3 = Msg("This is Tom. Nice talking to you.", Msg.TYPE_RECEIVED)
        msgList.add(msg3)
    }
}