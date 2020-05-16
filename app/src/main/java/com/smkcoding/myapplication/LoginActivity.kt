package com.smkcoding.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var inputName : String = ""
    private var inputPass : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener { validate() }
    }

    private fun validate() {
        inputName = edtNama.text.toString()
        inputPass = edtPass.text.toString()

        when {
            inputName.isEmpty() -> edtNama.error = "Username tidak boleh kosong"
            inputPass.isEmpty() -> edtPass.error = "Password tidak boleh kosong"
            else -> {
                showToast("Berhasil Login")
                goToMain()
            }
        }
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.apply {
            val userName = getString("NAME", "")
            val passWord = getString("PASS", "")
            edtNama.setText(userName)
            edtPass.setText(passWord)
        }

        saveData()
    }

    private fun saveData() {

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        editor.putString("NAME", edtNama.text.toString()).putString("PASS", edtPass.text.toString())
        startActivity(Intent(this, MainActivity::class.java))

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
