package com.example.bookinghotel.ui.dashboard.home.detailed_information

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bookinghotel.databinding.ActivityDetailedInformationBinding
import com.example.bookinghotel.domain.model.HotelSingleRoom
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/*
* Aktywnosc ktora odpowiedzialna za wyswietlenie danych o pokoju,
* czyli danych z modelu HotelSingleRoom
* oraz umozliwienie uzytkownikowi wynajecia pokoju
* */

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

        binding.reservationButton.setOnClickListener{
            roomReservation()
        }

        lifecycleScope.launchWhenCreated {
            viewModel.reservationState.collect {
                when(it){
                    is DetailedInformationViewModel.ReservationState.Success -> {
                        //TODO:: jesli uda sie zarezerwowac pokoj
                    }
                    is DetailedInformationViewModel.ReservationState.Error -> {
                        //TODO:: jesli chuja sie uda a nie zarezerwowac pokoj
                        Log.i("LOGIN ERROR", it.message)
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun roomReservation(){
        viewModel.roomReservation()
    }

}