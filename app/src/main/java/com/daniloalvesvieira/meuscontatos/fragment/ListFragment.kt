package com.daniloalvesvieira.meuscontatos.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.daniloalvesvieira.meuscontatos.DetalheActivity

import com.daniloalvesvieira.meuscontatos.R
import com.daniloalvesvieira.meuscontatos.adapter.ContatosAdapter
import com.daniloalvesvieira.meuscontatos.listener.OnItemClickListener
import com.daniloalvesvieira.meuscontatos.model.Contato
import kotlinx.android.synthetic.main.fragment_list.*


/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    private var mAdapter: ContatosAdapter? = null
    var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.rvListaContatos)

        var data: MutableList<Contato> = ArrayList<Contato>()

        for (i in 0 until 15) {
            val contato = Contato("Danilo", "danilo@teste.com.br", "(11) 99240-2815")
            contato.nome = "Danilo"
            data.add(contato)
        }
        setUpRecyclerView(data)

        return view
    }

    private fun setUpRecyclerView(data: List<Contato>) {

        mAdapter = ContatosAdapter(context, data)
        recyclerView!!.adapter = mAdapter
        recyclerView!!.layoutManager = LinearLayoutManager(context)

        mAdapter!!.setClickListener(object : OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                val contato = mAdapter!!.getItem(position)
                val i = Intent(activity, DetalheActivity::class.java)
                i.putExtra("NOME", contato.nome)
                i.putExtra("EMAIL", contato.email)
                i.putExtra("TELEFONE", contato.telefone)
                startActivity(i)

            }
        })
    }

}