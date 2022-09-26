package com.justus.mobilebmicalc

import android.icu.util.LocaleData.MeasurementSystem.SI
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.VISIBLE
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        var fragment: Fragment? = null
        var fragmentManager: FragmentManager? = null
        var fragmentTransaction: FragmentTransaction? = null

        fragment = fragment_Imperial()
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.replace(R.id.frameLayout, fragment)
        fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction!!.commit()
        //adding listener for tab select
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // creating cases for fragment
                when (tab.position) {
                    0 -> fragment = fragment_Imperial()
                    1 -> fragment = fragment_Standard()

                }
                val fm = supportFragmentManager
                val ft = fm.beginTransaction()
                ft.replace(R.id.frameLayout, fragment!!)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ft.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


//
  }
}