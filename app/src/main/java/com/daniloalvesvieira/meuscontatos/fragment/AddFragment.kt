package com.daniloalvesvieira.meuscontatos.fragment


import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*

import com.daniloalvesvieira.meuscontatos.R
import com.daniloalvesvieira.meuscontatos.room.AppDatabase


/**
 * A simple [Fragment] subclass.
 */
class AddFragment : Fragment() {

    var db: RoomDatabase? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_add, container, false)
        setHasOptionsMenu(true)

        db = Room.databaseBuilder(context, AppDatabase::class.java, "database-name").build()


        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_cadastro, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {


    }

}
