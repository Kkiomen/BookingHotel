package com.example.bookinghotel.ui.dashboard.reservation.detailed_reservation_information

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bookinghotel.databinding.ActivityDetailedInformationBinding
import com.example.bookinghotel.databinding.ActivityReservationDetailedInformationBinding
import com.example.bookinghotel.domain.model.HotelSingleRoom
import com.example.bookinghotel.domain.model.UserReservedRoom
import dagger.hilt.android.AndroidEntryPoint

/*
* Aktywnosc ktora odpowiedzialna za wyswietlenie danych zarezerwowanego przez uzytkownika pokoju,
* czyli danych z modelu UserReservedRoom
* */

@AndroidEntryPoint
class DetailedReservationInformationActivity : AppCompatActivity() {
    private val viewModel : DetailedReservationInformationViewModel by viewModels()
    private lateinit var binding : ActivityReservationDetailedInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReservationDetailedInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.userReservedRoom = intent.getSerializableExtra("UserRoom") as UserReservedRoom


        //binding.address.text = viewModel.hotelRoom?.hotel?.address1.toString()

        binding.unreservationButton.setOnClickListener{
            Toast.makeText(this, "Anulowanie rezerwacji przebiegło pomyślnie!", Toast.LENGTH_LONG).show()
        }


        //HOTEL INFORMATION
        binding.hotelName.text = viewModel.userReservedRoom.userHotel?.name.toString()
        binding.hotelAddress.text = viewModel.userReservedRoom.userHotel?.address1.toString()
        binding.hotelAddress2.text = viewModel.userReservedRoom.userHotel?.address2.toString()
        binding.hotelAddress3.text = viewModel.userReservedRoom.userHotel?.address3.toString()

        //RESERVATION INFORMATION
        binding.reservationPerson.text = viewModel.userReservedRoom.userRoom?.persons.toString()
        binding.reservationDescription.text = viewModel.userReservedRoom.userRoom?.description.toString()
        binding.reservationNumber.text = viewModel.userReservedRoom.userRoom?.number.toString()
    }

}