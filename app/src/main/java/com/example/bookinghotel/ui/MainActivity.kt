package com.example.bookinghotel.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookinghotel.R
import com.example.bookinghotel.ui.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.login_panel, LoginFragment())
                .commitAllowingStateLoss()
        }
    }
}