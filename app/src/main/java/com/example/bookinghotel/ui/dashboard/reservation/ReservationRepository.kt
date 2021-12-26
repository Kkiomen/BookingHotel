package com.example.bookinghotel.ui.dashboard.reservation

import android.util.Log
import com.example.bookinghotel.domain.services.HotelSingleRoomService
import javax.inject.Inject

class ReservationRepository @Inject constructor(
    private val hotelSingleRoomService: HotelSingleRoomService
) {

    val userReservedRoomList = hotelSingleRoomService.userReservedRoomList

    suspend fun findAllUserRooms() {
        hotelSingleRoomService.findAllUserRooms()
    }

}