package com.example.bookinghotel.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookinghotel.R
import com.example.bookinghotel.ui.login.LoginFragment
import com.example.bookinghotel.ui.register.RegisterFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.login_panel, LoginFragment())
                .replace(R.id.register_panel, RegisterFragment())
                .commit()
        }
    }
}