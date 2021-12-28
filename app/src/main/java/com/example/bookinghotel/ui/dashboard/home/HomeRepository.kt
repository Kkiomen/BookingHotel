package com.example.bookinghotel.ui.dashboard.home

import com.example.bookinghotel.domain.services.HotelSingleRoomService
import javax.inject.Inject

/*
* Repozytorium implementuje logike biznesowa oraz wybiera z niego funkcje ktore sa przydatne w danej aktywnosci
* udostepnia dla viewModelu:
* - liste hotelSingleRoom -> ktora zawiera liste wszystkich pokoi w systemie
* - findAllHotelsWithRooms() -> implementuje metode findAllHotelRooms() z logiki bieznesowej (HotelSingleRoomService)*/

class HomeRepository @Inject constructor(
    private val hotelSingleRoomService: HotelSingleRoomService
){

    val hotelSingleRoom = hotelSingleRoomService.hotelSingleRoomList

    suspend fun findAllHotelsWithRooms() {
        hotelSingleRoomService.findAllHotelRooms()
    }


}