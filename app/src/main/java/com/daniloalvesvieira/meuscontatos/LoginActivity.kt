package com.daniloalvesvieira.meuscontatos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
    }

    fun entrar(v: View) {
        if (etEmailLogin.text.isNotEmpty()) {
            if (etSenhaLogin.text.isNotEmpty()) {
                consultarFirebase()
            } else {
                Toast.makeText(this, R.string.empty_password, Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, R.string.empty_email, Toast.LENGTH_LONG).show()
        }
    }

    fun consultarFirebase() {
        mAuth.signInWithEmailAndPassword(etEmailLogin.text.toString(), etSenhaLogin.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Toast.makeText(this, it.exception!!.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                }
    }

    fun cadastrarLogin(v: View) {
        startActivity(Intent(this, CadastroLoginActivity::class.java))
    }
}
