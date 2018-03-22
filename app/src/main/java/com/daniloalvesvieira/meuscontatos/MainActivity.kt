package com.daniloalvesvieira.meuscontatos


import android.annotation.TargetApi
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
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

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val upArrow = resources.getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(resources.getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
        supportActionBar?.setHomeAsUpIndicator(upArrow);

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_add -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                changeFragment(AddFragment())
            }
            R.id.action_list -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                changeFragment(ListFragment())
            }
            R.id.action_info -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                changeFragment(InfoFragment())
            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                navigation.selectedItemId = R.id.action_list
                changeFragment(ListFragment())
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
        super.onBackPressed()
        finish()
    }

}
