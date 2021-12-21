package com.example.bookinghotel.ui.dashboard.reservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bookinghotel.databinding.FragmentHomeBinding
import com.example.bookinghotel.databinding.FragmentReservationBinding
import com.example.bookinghotel.ui.dashboard.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationFragment : Fragment() {

    private val viewModel : ReservationViewModel by viewModels()

    private lateinit var binding : FragmentReservationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentReservationBinding.inflate(layoutInflater)

        return binding.root
    }

}