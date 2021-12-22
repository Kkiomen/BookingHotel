package com.example.bookinghotel.domain.services

import android.util.Log
import com.example.bookinghotel.data.models.Hotel
import com.example.bookinghotel.data.models.Room
import com.example.bookinghotel.data.repostiories.HotelRoomRepository
import com.example.bookinghotel.domain.model.HotelSingleRoom
import com.example.bookinghotel.ui.dashboard.home.HomeViewModel
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*
    Klasa odpowiedzalna za przekonwertowanie surowego modelu z firebase (kolekcja hotels)
    w funkcjonalny model Hotel Single Room funckjonalny dla naszej aplikacji
    Czyli z wszystkich danych udostepnionych przez firebase wybieramy te dane ktore sa nam potrzebne w aplikacji np. obiekt pokoj i nazwa hotelu (bez adresow itp)
    tworzymy model ktory ma te dane nam przechowywac (HotelSingleRoom)
    i nastepnie zapisujemy i odczytujemy dane z tego modelu
 */

class HotelSingleRoomService @Inject constructor(
    private val hotelRoomRepository: HotelRoomRepository
) {

    private val hotels : MutableList<Hotel> = mutableListOf()
    val hotelSingleRoomList : MutableList<HotelSingleRoom> = mutableListOf()

    suspend fun findAllHotelRooms() = withContext(Dispatchers.IO) {
        val documents = hotelRoomRepository.findAllHotels()?.documents
        documents?.forEach{
            it.toObject<Hotel>()?.let { it1 -> hotels.add(it1) }
        }
        hotels.forEach{
            it.rooms?.forEach { room -> hotelSingleRoomList.add(HotelSingleRoom(it.name, room)) }
        }
    }

    //TODO:: funkcja ktora zmienia dostepnosc pokojuu na niedostepny
    //TODO:: przypisuje pokoj do uzytkownika
    //TODO:: przypisuje czas uzytkownikowi korzystania z pokoju

}