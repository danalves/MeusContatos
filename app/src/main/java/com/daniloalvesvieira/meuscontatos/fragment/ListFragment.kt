package com.daniloalvesvieira.meuscontatos.fragment


import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.AdapterContextMenuInfo
import com.daniloalvesvieira.meuscontatos.DetalheActivity
import com.daniloalvesvieira.meuscontatos.EdicaoActivity
import com.daniloalvesvieira.meuscontatos.R
import com.daniloalvesvieira.meuscontatos.adapter.ContatosAdapter
import com.daniloalvesvieira.meuscontatos.listener.OnItemClickListener
import com.daniloalvesvieira.meuscontatos.model.Contato
import com.daniloalvesvieira.meuscontatos.room.AppDatabase
import java.io.Serializable


/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment(), Serializable {

    private var mAdapter: ContatosAdapter? = null
    var recyclerView: RecyclerView? = null
    var db: AppDatabase? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.rvListaContatos)

        db = Room.databaseBuilder(context, AppDatabase::class.java, "database-name")
                .allowMainThreadQueries().build()

        atualizarLista()

        return view
    }

    fun atualizarLista() {
        val data = db!!.contatoDao().getAll()
        setUpRecyclerView(data)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {

            4 -> {
                val contato = mAdapter!!.getItem(mAdapter!!.getPosicaoContextMenu())
                val dadosContato =  "Contato Compartilhado\n\n" +
                        "Nome: " + contato.nome + "\n" +
                        "Tel: " + contato.telefone + "\n" +
                        "E-mail: " + contato.email + "\n" +
                        "Endereço: " + contato.endereco + "\n"

                val sendIntent = Intent(Intent.ACTION_SEND)
                sendIntent.putExtra(Intent.EXTRA_TEXT, dadosContato)
                sendIntent.type = "text/plain"
                startActivity(Intent.createChooser(sendIntent, resources.getString(R.string.share_chooser)))
            }

            // 5 - Alterar Contato
            5 -> {
                val contato = mAdapter!!.getItem(mAdapter!!.getPosicaoContextMenu())
                val i = Intent(activity, EdicaoActivity::class.java)
                i.putExtra("ID", contato.contatoId)
                i.putExtra("NOME", contato.nome)
                i.putExtra("EMAIL", contato.email)
                i.putExtra("TELEFONE", contato.telefone)
                i.putExtra("ENDERECO", contato.endereco)
                startActivityForResult(i, 200)
            }

            // 6 - Excluir Contato
            6 -> {
                db!!.contatoDao().delete(mAdapter!!.getItem(mAdapter!!.getPosicaoContextMenu()))
                atualizarLista()
            }
        }

        return super.onContextItemSelected(item)
    }


    private fun setUpRecyclerView(data: List<Contato>) {

        mAdapter = ContatosAdapter(context, data)
        recyclerView!!.adapter = mAdapter
        recyclerView!!.layoutManager = LinearLayoutManager(context)

        mAdapter!!.setClickListener(object : OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                val contato = mAdapter!!.getItem(position)
                val i = Intent(activity, DetalheActivity::class.java)
                i.putExtra("ID", contato.contatoId)
                i.putExtra("NOME", contato.nome)
                i.putExtra("EMAIL", contato.email)
                i.putExtra("TELEFONE", contato.telefone)
                i.putExtra("ENDERECO", contato.endereco)
//                i.putExtra("LISTFRAGMENT",this@ListFragment)
                startActivityForResult(i, 100)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        atualizarLista()
    }


}