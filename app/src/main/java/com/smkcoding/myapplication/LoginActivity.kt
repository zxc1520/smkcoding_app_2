package com.smkcoding.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var auth: FirebaseAuth? = null
    private var RC_SIGN_IN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progress.visibility = View.GONE
        login.setOnClickListener(this)

        auth = FirebaseAuth.getInstance()

        if (auth!!.currentUser == null) {
        } else {
             intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onActivityResult (
        requestCode: Int,
        resultCode: Int,
        data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Berhasil Login", Toast.LENGTH_SHORT).show()
                intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                progress.visibility = View.GONE
                Toast.makeText(this, "Gagal Login", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(v: View?) {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
                .setIsSmartLockEnabled(false)
                .build(),
        RC_SIGN_IN)

        progress.visibility = View.VISIBLE
    }

}
