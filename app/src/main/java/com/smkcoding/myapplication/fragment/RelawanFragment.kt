package com.smkcoding.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.smkcoding.myapplication.*
import com.smkcoding.myapplication.R
import kotlinx.android.synthetic.main.frame_relawan.*

class RelawanFragment : Fragment() {

    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth
    lateinit var dataRelawan : ArrayList<RelawanModel>
    lateinit var listRelawan: ArrayList<Relawan>

    private fun getData() {
        Toast.makeText(context, "Tunggu sebentar...", Toast.LENGTH_SHORT).show()

        auth = FirebaseAuth.getInstance()
        val getUserID : String = auth?.currentUser?.uid.toString()
        ref = FirebaseDatabase.getInstance().getReference()
        ref.child(getUserID).child("Relawan").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Gagal memuat !", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                dataRelawan = java.util.ArrayList<RelawanModel>()
                for (snap in snapshot.children) {
                    val relawan = snap.getValue(RelawanModel::class.java)

                    relawan?.key
                    dataRelawan.add(relawan!!)
                }

                rv_listRelawan.layoutManager = LinearLayoutManager(context)
                rv_listRelawan.adapter = RelawanAdapter(context!!, dataRelawan)

                Toast.makeText(context, "Berhasil dimuat", Toast.LENGTH_SHORT).show()
            }

        })
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
        getData()
        fab.setOnClickListener {
            val intent = Intent(getActivity(), RelawanActivity::class.java)
            getActivity()?.startActivity(intent)
        }
    }

}