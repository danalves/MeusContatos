package com.daniloalvesvieira.meuscontatos

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.daniloalvesvieira.meuscontatos.model.Contato
import com.daniloalvesvieira.meuscontatos.room.AppDatabase
import kotlinx.android.synthetic.main.activity_detalhe.*

class DetalheActivity : AppCompatActivity() {

    var contato: Contato? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)

        if (intent != null){
            tvNomeDetalhe.text = intent.getStringExtra("NOME")
            tvEmailDetalhe.text = intent.getStringExtra("EMAIL")
            tvTelDetalhe.text = intent.getStringExtra("TELEFONE")
            tvEnderecoDetalhe.text = intent.getStringExtra("ENDERECO")

            contato.contatoId = intent("NOME")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_detalhe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.iEditar-> {

            }
            R.id.iDeletar-> {
                val db = Room.databaseBuilder(this, AppDatabase::class.java, "database-name")
                        .allowMainThreadQueries().build()
                db.contatoDao().delete()

            }

        }

        return true
    }

}
