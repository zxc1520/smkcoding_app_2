package com.smkcoding.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_relawan.*

class RelawanActivity : AppCompatActivity() {

    private var Nama : EditText? = null
    private var Email : EditText? = null
    private var Telp : EditText? = null
    private var Alamat : EditText? = null
    lateinit var ref : DatabaseReference
    private var auth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relawan)

        Nama = findViewById<EditText>(R.id.nama)
        Email = findViewById(R.id.email)
        Telp = findViewById(R.id.telp)
        Alamat = findViewById(R.id.alamat)

        ref = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        simpan.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {

        val getNama: String = Nama?.text.toString()
        val getEmail : String = Email?.text.toString()
        val getTelp : String = Telp?.text.toString()
        val getAlamat : String = Alamat?.text.toString()
        val getUserID : String = auth?.currentUser?.uid.toString()

        if (getNama.isEmpty() && getEmail.isEmpty() && getTelp.isEmpty() && getAlamat.isEmpty() && getUserID.isEmpty()) {
            Toast.makeText(this@RelawanActivity, "Tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show()
        } else {
            val relawan = RelawanModel(getNama, getEmail, getTelp, getAlamat, getUserID)
            ref.child(getUserID).child("Relawan").push().setValue(relawan).addOnCompleteListener {
                Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
