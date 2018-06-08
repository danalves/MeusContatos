package com.daniloalvesvieira.meuscontatos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_cadastro_login.*
import kotlinx.android.synthetic.main.activity_login.*

class CadastroLoginActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_login)

        mAuth = FirebaseAuth.getInstance()
    }

    fun cadastrar(v: View) {

        if (etEmailLoginCadastro.text.isNotEmpty() && etEmailLoginCadastro2.text.isNotEmpty()) {
            if (etSenhaLoginCadastro.text.isNotEmpty() && etSenhaLoginCadastro2.text.isNotEmpty()) {
                if (etEmailLoginCadastro.text.toString() == etEmailLoginCadastro2.text.toString()) {
                    if (etSenhaLoginCadastro.text.toString() == etSenhaLoginCadastro2.text.toString()) {
                        inserirFirebase()
                    } else {
                        Toast.makeText(this, "Campos de Senha diferentes", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, "Campos de E-mail diferentes", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Preencher Senha", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Preencher E-mail", Toast.LENGTH_LONG).show()
        }
    }

    fun inserirFirebase() {
        mAuth.createUserWithEmailAndPassword(etEmailLoginCadastro.text.toString(), etSenhaLoginCadastro.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        Log.i("TAG", "Sucesso no cadastro do usuário")
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Log.i("TAG", "Erro no cadastro do usuário")
                        Toast.makeText(this, it.exception!!.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                }
    }
}
