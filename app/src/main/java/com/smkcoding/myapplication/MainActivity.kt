package com.smkcoding.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.smkcoding.myapplication.fragment.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val tabName = arrayOf("Beranda", "Provinsi", "Relawan", "Profil")
    val tabIcon = arrayOf(R.drawable.ic_home, R.drawable.ic_protection, R.drawable.ic_volunteer, R.drawable.ic_person)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ViewPagerAdapter(this)
        view_pager.setAdapter(adapter)
        TabLayoutMediator(tab_layout, view_pager,
            TabLayoutMediator.TabConfigurationStrategy {tab, position ->
                tab.text = tabName[position]
                tab.icon = ResourcesCompat.getDrawable(resources, tabIcon[position], null)
            }).attach()
    }
}
