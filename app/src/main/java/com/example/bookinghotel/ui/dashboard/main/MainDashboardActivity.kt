package com.example.bookinghotel.ui.dashboard.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.bookinghotel.R
import com.example.bookinghotel.databinding.MainDashboardActivityLayoutBinding
import com.example.bookinghotel.ui.dashboard.home.HomeFragment
import com.example.bookinghotel.ui.dashboard.reservation.ReservationFragment
import com.example.bookinghotel.ui.dashboard.settings.SettingsFragment
import com.example.bookinghotel.ui.login.LoginFragment
import com.example.bookinghotel.ui.register.RegisterFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            val homeFragmentTransaction = supportFragmentManager.beginTransaction()
            when(it){
                R.id.nav_home -> {
                    homeFragmentTransaction
                        .replace(binding.homePanel.id, HomeFragment())

                    Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_calendar -> {
                    homeFragmentTransaction
                        .replace(binding.homePanel.id, ReservationFragment())

                    Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_like -> {
                    Toast.makeText(this, "LIKES PAGE", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_settings -> {
                    homeFragmentTransaction
                        .replace(binding.homePanel.id, SettingsFragment())

                    Toast.makeText(this, "SETTINGS", Toast.LENGTH_SHORT).show()
                }
            }
            homeFragmentTransaction.commit()
        }
    }

}