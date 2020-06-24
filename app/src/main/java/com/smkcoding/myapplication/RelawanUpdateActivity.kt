package com.smkcoding.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_relawan_update.*

class RelawanUpdateActivity : AppCompatActivity() {

    private var namaBaru : EditText? = null
    private var emailBaru : EditText? = null
    private var telpBaru : EditText? = null
    private var alamatBaru : EditText? = null
    private var database : DatabaseReference? = null
    private var auth : FirebaseAuth? = null
    private var cekNama : String? = null
    private var cekEmail : String? = null
    private var cekTelp : String? = null
    private var cekAlamat : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relawan_update)

        namaBaru = findViewById(R.id.nama)
        emailBaru = findViewById(R.id.email)
        telpBaru = findViewById(R.id.telp)
        alamatBaru = findViewById(R.id.alamat)

        // Database Instance
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference()

        getData()
        btnUpdate.setOnLongClickListener {
            cekNama = namaBaru?.text.toString()
            cekEmail = emailBaru?.text.toString()
            cekTelp = telpBaru?.text.toString()
            cekAlamat = alamatBaru?.text.toString()

            (if (cekNama!!.isEmpty() || cekEmail!!.isEmpty() || cekTelp!!.isEmpty() || cekAlamat!!.isEmpty()) {
                Toast.makeText(this, "Tidak boleh ada yang kosong !", Toast.LENGTH_SHORT).show()
            } else {

                val relawanBaru = RelawanModel(cekNama!!, cekEmail!!, cekTelp!!, cekAlamat!!)
                val getUserID : String = auth?.currentUser?.uid.toString()
                val getKey : String = intent?.getStringExtra("getPrimaryKey").toString()
                database!!.child(getUserID).child("Relawan")
                    .child(getKey).setValue(relawanBaru)
                    .addOnCompleteListener {
                        Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                        finish()
                    }

            }) as Boolean
        }
    }

    private fun getData() {

    }
}
