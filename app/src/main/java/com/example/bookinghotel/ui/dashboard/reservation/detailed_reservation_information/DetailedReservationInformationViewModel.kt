package com.example.bookinghotel.ui.dashboard.reservation.detailed_reservation_information

import androidx.lifecycle.ViewModel
import com.example.bookinghotel.domain.model.UserReservedRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailedReservationInformationViewModel @Inject constructor(
) : ViewModel(){
    var userReservedRoom : UserReservedRoom = UserReservedRoom()
}
