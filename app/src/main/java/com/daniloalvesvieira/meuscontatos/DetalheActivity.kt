package com.daniloalvesvieira.meuscontatos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detalhe.*

class DetalheActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)

        if (intent != null){
            tvNomeDetalhe.text = intent.getStringExtra("NOME")
            tvEmailDetalhe.text = intent.getStringExtra("EMAIL")
            tvTelDetalhe.text = intent.getStringExtra("TELEFONE")
            tvEnderecoDetalhe.text = intent.getStringExtra("ENDERECO")
        }


    }
}
