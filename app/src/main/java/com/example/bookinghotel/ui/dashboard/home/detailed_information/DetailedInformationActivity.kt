package com.example.bookinghotel.ui.dashboard.home.detailed_information

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bookinghotel.databinding.ActivityDetailedInformationBinding
import com.example.bookinghotel.domain.model.HotelSingleRoom
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedInformationActivity : AppCompatActivity() {
    private val viewModel : DetailedInformationViewModel by viewModels()
    private lateinit var binding : ActivityDetailedInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailedInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.hotelRoom = intent.getSerializableExtra("HotelRoom") as HotelSingleRoom?

        binding.testTextView.text = viewModel.hotelRoom.toString()
    }

}