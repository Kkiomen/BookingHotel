package com.example.bookinghotel.ui.dashboard.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.bookinghotel.R
import com.example.bookinghotel.backgroundServices.CheckDateService
import com.example.bookinghotel.databinding.MainDashboardActivityLayoutBinding
import com.example.bookinghotel.ui.dashboard.home.HomeFragment
import com.example.bookinghotel.ui.dashboard.reservation.ReservationFragment
import com.example.bookinghotel.ui.dashboard.settings.SettingsFragment
import com.example.bookinghotel.ui.login.LoginFragment
import com.example.bookinghotel.ui.register.RegisterFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

/*
* Glowna aktywnosc aplikacji
* zawiera bottom menu
* wybiera fragmenty ktore maja sie wyswietlic (HomeFragment, ReservationFragment, SettingsFragment, ProfileFragment)
* TODO:: stworzyc ProfileFragment,  ProfileRepository, ProfileViewModel
*  */

@AndroidEntryPoint
class MainDashboardActivity : AppCompatActivity() {

    private lateinit var binding : MainDashboardActivityLayoutBinding
    private var chipNavigationBar: ChipNavigationBar? = null
    private lateinit var homeFragmentTransaction : FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainDashboardActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //workerManager
        val repeatingRequest = PeriodicWorkRequestBuilder<CheckDateService>(1, TimeUnit.DAYS)
            .build()

        val workManager = WorkManager.getInstance(applicationContext)
        workManager.enqueue(repeatingRequest)

        //startowy fragment (Home Fragment)
//        homeFragmentTransaction = supportFragmentManager.beginTransaction()
//            .replace(binding.homePanel.id, HomeFragment())
//        homeFragmentTransaction.commit()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        chipNavigationBar = binding.bottomNavBar

        setUpTapBar()
    }

    private fun setUpTapBar(){
        chipNavigationBar?.setOnItemSelectedListener(){
            homeFragmentTransaction = supportFragmentManager.beginTransaction()
            when(it){
                R.id.nav_home -> {
                    homeFragmentTransaction
                        .replace(binding.homePanel.id, HomeFragment())

                    Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_calendar -> {
                    homeFragmentTransaction
                        .replace(binding.homePanel.id, ReservationFragment())

                    Toast.makeText(this, "RESERVATION", Toast.LENGTH_SHORT).show()
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