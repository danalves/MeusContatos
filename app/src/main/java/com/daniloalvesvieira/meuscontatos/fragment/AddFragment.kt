package com.daniloalvesvieira.meuscontatos.fragment


import android.arch.persistence.room.RoomDatabase
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*

import com.daniloalvesvieira.meuscontatos.R
import com.daniloalvesvieira.meuscontatos.model.Contato
import kotlinx.android.synthetic.main.fragment_add.*
import com.daniloalvesvieira.meuscontatos.room.AppDatabase
import android.arch.persistence.room.Room
import com.daniloalvesvieira.meuscontatos.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class AddFragment : Fragment() {

    var db: AppDatabase? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_add, container, false)
        setHasOptionsMenu(true)

        db = Room.databaseBuilder(context, AppDatabase::class.java, "database-name")
                .allowMainThreadQueries().build()



        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_cadastro, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.iSalvar -> {
                inserirContato()
                etNomeCadastro.setText("")
                etEmailCadastro.setText("")
                etTelCadastro.setText("")
                etEnderecoCadastro.setText("")

                val mActivity = activity as MainActivity
                val mAdapter = mActivity.viewpager.adapter as MainActivity.ViewPagerAdapter
                val listFragment = mAdapter.getItem(1) as ListFragment
                listFragment.atualizarLista()

                mActivity.viewpager.currentItem = 1
            }

        }

        return true
    }

    private fun inserirContato() {

        var novoContato = Contato(etNomeCadastro.text.toString(), etEmailCadastro.text.toString(),
                etTelCadastro.text.toString(), etEnderecoCadastro.text.toString())

        db!!.contatoDao().insertAll(novoContato)
    }


}
