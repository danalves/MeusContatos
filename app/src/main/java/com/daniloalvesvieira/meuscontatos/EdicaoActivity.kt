package com.daniloalvesvieira.meuscontatos

import android.app.Activity
import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.daniloalvesvieira.meuscontatos.model.Contato
import com.daniloalvesvieira.meuscontatos.room.AppDatabase
import kotlinx.android.synthetic.main.activity_edicao.*

class EdicaoActivity : AppCompatActivity() {

    lateinit var contato: Contato
    var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edicao)

        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = "Editar Contato"

        if (intent != null) {
            etNomeEdit.setText(intent.getStringExtra("NOME"))
            etEmailEdit.setText(intent.getStringExtra("EMAIL"))
            etTelEdit.setText(intent.getStringExtra("TELEFONE"))
            etEnderecoEdit.setText(intent.getStringExtra("ENDERECO"))

            contato = Contato(
                    intent.getStringExtra("NOME"),
                    intent.getStringExtra("EMAIL"),
                    intent.getStringExtra("TELEFONE"),
                    intent.getStringExtra("ENDERECO"))

            contato!!.contatoId = intent.getLongExtra("ID", 0)
        }

        db = Room.databaseBuilder(this, AppDatabase::class.java, "database-name")
                .allowMainThreadQueries().build()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater!!.inflate(R.menu.menu_cadastro, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.iSalvar -> {
                alterarContato()

                setResult(Activity.RESULT_OK)
                finish()

            }

        }
        return true
    }

    private fun alterarContato() {

        contato.nome = etNomeEdit.text.toString()
        contato.email = etEmailEdit.text.toString()
        contato.telefone = etTelEdit.text.toString()
        contato.endereco = etEnderecoEdit.text.toString()

        db!!.contatoDao().updateContato(contato)
    }

}
