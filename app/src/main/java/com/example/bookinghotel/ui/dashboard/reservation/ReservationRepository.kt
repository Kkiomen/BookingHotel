package com.example.bookinghotel.ui.dashboard.reservation

import android.util.Log
import com.example.bookinghotel.domain.services.HotelSingleRoomService
import javax.inject.Inject

/*
* Repozytorium implementuje logike biznesowa oraz wybiera z niego funkcje ktore sa przydatne w danej aktywnosci
* udostepnia dla viewModelu:
* - liste userReservedRoomList -> ktora zawiera liste wszystkich zarezerwowanych pokoi przez uzytkownika w systemie
* - findAllUserRooms() -> implementuje metode findAllUserRooms() z logiki bieznesowej (HotelSingleRoomService)*/

class ReservationRepository @Inject constructor(
    private val hotelSingleRoomService: HotelSingleRoomService
) {

    val userReservedRoomList = hotelSingleRoomService.userReservedRoomList

    suspend fun findAllUserRooms() {
        hotelSingleRoomService.findAllUserRooms()
    }

}