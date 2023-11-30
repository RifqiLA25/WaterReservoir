package com.example.iot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.iot.databinding.ActivityForgotPasswordBinding
import com.example.iot.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(fHome())
        //definisi widget
        val bottomnav = findViewById<BottomNavigationView>(R.id.bot_nav_menu)
        bottomnav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bot_home_menu -> {
                    loadFragment(fHome())
                    true
                }
            }

            when(it.itemId){
                R.id.bot_grafik_menu -> {
                    loadFragment(fGrafik())
                    true
                }
            }

            when(it.itemId){
                R.id.bot_Profile_menu -> {
                    loadFragment(fProfile())
                    true
                }
                else -> {false}
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.f_container,fragment)
        transaction.commit()
    }
}