package com.smkcoding.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smkcoding.myapplication.MyFriend
import com.smkcoding.myapplication.MyFriendAdapter
import com.smkcoding.myapplication.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.frame_myfriends.*

class MyFriendsFragment : Fragment() {

    lateinit var listTeman : ArrayList<MyFriend>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frame_myfriends, container, false)
    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

    private fun simulasiDataTeman() {
        listTeman = ArrayList()
        listTeman.add(MyFriend("Hamzah", "laki-laki", "hamzah@gmail.com", "085123456", "Kediri"))
        listTeman.add(MyFriend("Juliandri", "Laki-laki", "july@gmail.com", "081234567", "Sidoarjo"))
    }

    private fun tampilTeman() {
        rv_listMyFriend.layoutManager = LinearLayoutManager(activity)
        rv_listMyFriend.adapter = MyFriendAdapter(activity!!, listTeman)
    }

    private fun initView() {
        simulasiDataTeman()
        tampilTeman()
    }

}