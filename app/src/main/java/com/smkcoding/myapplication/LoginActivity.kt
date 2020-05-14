package com.smkcoding.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smkcoding.myapplication.startup.Preferences
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var inputName : String = ""
    private var inputPass : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    private fun validate() {
        inputName = txtNama.text.toString()
        inputPass = txtPass.text.toString()

        when {
            inputName.isEmpty() -> txtNama.error = "Username tidak boleh kosong"
            inputPass.isEmpty() -> txtPass.error = "Password tidak boleh kosong"
        }
    }

}
