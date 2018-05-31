package com.daniloalvesvieira.meuscontatos

import android.app.Activity
import android.arch.persistence.room.Room
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.daniloalvesvieira.meuscontatos.fragment.ListFragment
import com.daniloalvesvieira.meuscontatos.model.Contato
import com.daniloalvesvieira.meuscontatos.room.AppDatabase
import kotlinx.android.synthetic.main.activity_detalhe.*

class DetalheActivity : AppCompatActivity() {

    lateinit var contato: Contato
    // var listFragment: ListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)

        if (intent != null) {
            tvNomeDetalhe.text = intent.getStringExtra("NOME")
            tvEmailDetalhe.text = intent.getStringExtra("EMAIL")
            tvTelDetalhe.text = intent.getStringExtra("TELEFONE")
            tvEnderecoDetalhe.text = intent.getStringExtra("ENDERECO")


            contato = Contato(
                    intent.getStringExtra("NOME"),
                    intent.getStringExtra("EMAIL"),
                    intent.getStringExtra("TELEFONE"),
                    intent.getStringExtra("ENDERECO"))

            contato!!.contatoId = intent.getLongExtra("ID", 0)

//            listFragment = intent.getSerializableExtra("LISTFRAGMENT") as ListFragment
        }
    }

    fun ligar(v: View) {
        val intentTel = Intent(Intent.ACTION_VIEW)
        intentTel.data = Uri.parse("tel:" + contato.telefone)
        startActivity(intentTel)
    }

    fun compartilhar(v: View) {

        val dadosContato = "NOME: " + contato.nome + "\n" +
                           "E-MAIL: " + contato.email

        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.putExtra(Intent.EXTRA_TEXT, dadosContato)
        sendIntent.type = "text/plain"
        startActivity(Intent.createChooser(sendIntent, "Escolhe aí"))
    }

    fun mostrarEndereco(v: View) {
        val i = Intent(this, MapActivity::class.java)
        i.putExtra("ENDERECO", contato.endereco)
        startActivity(i)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_detalhe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.iEditar -> {
                val i = Intent(this, EdicaoActivity::class.java)
                i.putExtra("ID", contato.contatoId)
                i.putExtra("NOME", contato.nome)
                i.putExtra("EMAIL", contato.email)
                i.putExtra("TELEFONE", contato.telefone)
                i.putExtra("ENDERECO", contato.endereco)
                startActivityForResult(i, 200)
            }
            R.id.iDeletar -> {
                val db = Room.databaseBuilder(this, AppDatabase::class.java, "database-name")
                        .allowMainThreadQueries().build()
                db.contatoDao().delete(contato)
  
                setResult(Activity.RESULT_OK)
                finish()
            }

        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 200) {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

}
