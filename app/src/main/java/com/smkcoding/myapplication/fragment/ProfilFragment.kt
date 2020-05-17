package com.smkcoding.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.smkcoding.myapplication.LoginActivity
import com.smkcoding.myapplication.R
import kotlinx.android.synthetic.main.frame_profil.*

class ProfilFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frame_profil, container, false)
    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        //btnLogout.setOnClickListener { logout() }
    }

//    private fun logout() {
//
//        val pref = PreferenceManager.getDefaultSharedPreferences(context)
//
//        val editor = pref.edit()
//        editor.remove("NAME").remove("PASS").commit()
//        //startActivity(Intent(this, LoginActivity::class.java))
//
//    }
}