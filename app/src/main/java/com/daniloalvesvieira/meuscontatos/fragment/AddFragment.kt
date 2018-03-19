package com.daniloalvesvieira.meuscontatos.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*

import com.daniloalvesvieira.meuscontatos.R


/**
 * A simple [Fragment] subclass.
 */
class AddFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_add, container, false)
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_cadastro, menu)
    }

}
