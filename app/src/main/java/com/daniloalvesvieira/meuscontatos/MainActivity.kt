package com.daniloalvesvieira.meuscontatos


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.daniloalvesvieira.meuscontatos.fragment.AddFragment
import com.daniloalvesvieira.meuscontatos.fragment.InfoFragment
import com.daniloalvesvieira.meuscontatos.fragment.ListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(this)
        navigation.selectedItemId = R.id.action_list

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_add -> changeFragment(AddFragment())
            R.id.action_list -> changeFragment(ListFragment())
            R.id.action_info -> changeFragment(InfoFragment())
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.home -> {
                val count = fragmentManager.backStackEntryCount

                if (count == 0) {
                    super.onBackPressed()
                } else {
                    fragmentManager.popBackStack()
                }
            }
        }
        return true
    }


    private fun changeFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_main, fragment)
        fragmentTransaction.addToBackStack(fragment.toString())
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {

        val count = fragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
        } else {
            fragmentManager.popBackStack()
        }

    }

}
