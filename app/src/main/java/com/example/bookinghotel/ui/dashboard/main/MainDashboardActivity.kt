package com.example.bookinghotel.ui.dashboard.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.example.bookinghotel.R
import com.example.bookinghotel.databinding.ActivityMainBinding
import com.example.bookinghotel.databinding.MainDashboardActivityLayoutBinding
import com.ismaeldivita.chipnavigation.ChipNavigationBar


class MainDashboardActivity : AppCompatActivity() {

    private lateinit var binding : MainDashboardActivityLayoutBinding
    private var chipNavigationBar: ChipNavigationBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainDashboardActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        chipNavigationBar = binding.bottomNavBar
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