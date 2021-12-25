package com.example.bookinghotel.ui.dashboard.home.detailed_information

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.bookinghotel.domain.model.HotelSingleRoom
import com.example.bookinghotel.domain.services.HotelSingleRoomService
import javax.inject.Inject

class DetailedinformationRepository @Inject constructor(
    private val hotelSingleRoomService: HotelSingleRoomService
) {

    fun roomReservation(room : HotelSingleRoom){
        hotelSingleRoomService.reserveRoom(room)
    }

}