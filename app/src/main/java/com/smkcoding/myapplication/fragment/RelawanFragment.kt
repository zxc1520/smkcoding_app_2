package com.smkcoding.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smkcoding.myapplication.R
import com.smkcoding.myapplication.Relawan
import com.smkcoding.myapplication.RelawanActivity
import com.smkcoding.myapplication.RelawanAdapter
import kotlinx.android.synthetic.main.frame_relawan.*

class RelawanFragment : Fragment() {

    lateinit var listRelawan: ArrayList<Relawan>

    private fun dataRelawan() {
        listRelawan = ArrayList()
        listRelawan.add(Relawan("Andi", "andika@gmail.com", "0123456789", "Jl.Manggis"))
    }

    private fun showRelawan() {
        rv_listRelawan.layoutManager = LinearLayoutManager(activity)
        rv_listRelawan.adapter = RelawanAdapter(activity!!, listRelawan)
    }

    private fun initView() {
        dataRelawan()
        showRelawan()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frame_relawan, container, false)
    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        fab.setOnClickListener {
            val intent = Intent(getActivity(), RelawanActivity::class.java)
            getActivity()?.startActivity(intent)
        }
    }

}