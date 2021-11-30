package com.example.bookinghotel.ui.dashboard.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.bookinghotel.R
import com.example.bookinghotel.databinding.ActivityMainBinding
import com.ismaeldivita.chipnavigation.ChipNavigationBar


class MainDashboardActivity : AppCompatActivity() {

    var chipNavigationBar: ChipNavigationBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_dashboard_activity_layout)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        chipNavigationBar = findViewById(R.id.bottom_nav_bar);
        setUpTapBar()
    }


    private fun setUpTapBar(){
        chipNavigationBar?.setOnItemSelectedListener(){
            when(it){
                R.id.nav_home -> Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show()
                R.id.nav_calendar -> Toast.makeText(this, "RESERVATION" , Toast.LENGTH_SHORT).show()
                R.id.nav_like -> Toast.makeText(this, "LIKES PAGE", Toast.LENGTH_SHORT).show()
                R.id.nav_settings -> Toast.makeText(this, "SETTINGS", Toast.LENGTH_SHORT).show()
            }
        }
    }

}