package com.daniloalvesvieira.meuscontatos


import android.annotation.TargetApi
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.daniloalvesvieira.meuscontatos.fragment.AddFragment
import com.daniloalvesvieira.meuscontatos.fragment.InfoFragment
import com.daniloalvesvieira.meuscontatos.fragment.ListFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.file.Files.size
import android.support.v4.app.FragmentStatePagerAdapter
import com.daniloalvesvieira.meuscontatos.room.AppDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    var prevMenuItem: MenuItem? = null

    lateinit var addFragment: AddFragment
    lateinit var listFragment: ListFragment
    lateinit var infoFragment: InfoFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(this)
//        navigation.selectedItemId = R.id.action_list

        viewpager.addOnPageChangeListener(this)
        setupViewPager(viewpager);

        viewpager.currentItem = 1

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val upArrow = resources.getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(resources.getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
        supportActionBar?.setHomeAsUpIndicator(upArrow);

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_add -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
//                changeFragment(AddFragment())
                viewpager.currentItem = 0
            }
            R.id.action_list -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
//                changeFragment(ListFragment())
                viewpager.currentItem = 1
            }
            R.id.action_info -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
//                changeFragment(InfoFragment())
                viewpager.currentItem = 2
            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
//                supportActionBar?.setDisplayHomeAsUpEnabled(false)
//                navigation.selectedItemId = R.id.action_list
//                changeFragment(ListFragment())
                viewpager.currentItem = 1
            }
//            Para ser tratado no fragment
            R.id.iSalvar -> {
                return false
            }


        }
        return true
    }

//    private fun changeFragment(fragment: Fragment) {
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.content_main, fragment)
//        fragmentTransaction.addToBackStack(fragment.toString())
//        fragmentTransaction.commit()
//    }

    override fun onPageSelected(position: Int) {
        if (prevMenuItem != null) {
            prevMenuItem!!.isChecked = false
        }
        else
        {
            navigation.getMenu().getItem(0).isChecked = false
        }
        navigation.getMenu().getItem(position).isChecked = true
        prevMenuItem = navigation.menu.getItem(position)

        if (position == 1) supportActionBar?.setDisplayHomeAsUpEnabled(false) else supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        addFragment = AddFragment()
        listFragment = ListFragment()
        infoFragment = InfoFragment()

        adapter.addFragment(addFragment)
        adapter.addFragment(listFragment)
        adapter.addFragment(infoFragment)
        viewPager.adapter = adapter
    }

    inner class ViewPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position)
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment) {
            mFragmentList.add(fragment)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }



}
