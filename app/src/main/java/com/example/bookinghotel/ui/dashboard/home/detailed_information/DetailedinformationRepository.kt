package com.example.bookinghotel.ui.dashboard.home.detailed_information

import com.example.bookinghotel.domain.model.HotelSingleRoom
import com.example.bookinghotel.domain.services.HotelSingleRoomService
import javax.inject.Inject

/*
* Repozytorium ktore definiuje funkcje ktore sa potrzebne w aktywnosci
* czyli w tym przypadku zarezerwowanie pokoju przez uzytkownika
* */

class DetailedinformationRepository @Inject constructor(
    private val hotelSingleRoomService: HotelSingleRoomService
) {

    fun roomReservation(room : HotelSingleRoom){
        hotelSingleRoomService.reserveRoom(room)
    }

}