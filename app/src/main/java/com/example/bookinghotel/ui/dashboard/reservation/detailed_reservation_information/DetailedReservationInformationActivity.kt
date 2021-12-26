package com.example.bookinghotel.ui.dashboard.reservation.detailed_reservation_information

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bookinghotel.databinding.ActivityDetailedInformationBinding
import com.example.bookinghotel.databinding.ActivityReservationDetailedInformationBinding
import com.example.bookinghotel.domain.model.HotelSingleRoom
import com.example.bookinghotel.domain.model.UserReservedRoom
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedReservationInformationActivity : AppCompatActivity() {
    private val viewModel : DetailedReservationInformationViewModel by viewModels()
    private lateinit var binding : ActivityReservationDetailedInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReservationDetailedInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.userReservedRoom = intent.getSerializableExtra("UserRoom") as UserReservedRoom

        binding.testTextView.text = viewModel.userReservedRoom.toString()
    }

}