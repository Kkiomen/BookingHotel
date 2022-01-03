package com.example.bookinghotel.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.bookinghotel.R
import com.mikhaellopez.circularprogressbar.CircularProgressBar

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        val circularProgressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
        circularProgressBar.apply {

            setProgressWithAnimation(200f, 3000)
            progressMax = 200f
            progressBarColor = Color.WHITE
            progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

            backgroundProgressBarColor = Color.GRAY
            backgroundProgressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
        }

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}

