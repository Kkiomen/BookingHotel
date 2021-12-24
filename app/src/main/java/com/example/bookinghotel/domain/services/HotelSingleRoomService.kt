package com.example.bookinghotel.domain.services

import android.util.Log
import com.example.bookinghotel.data.models.Hotel
import com.example.bookinghotel.data.models.toSingleHotel
import com.example.bookinghotel.data.repostiories.HotelRoomRepository
import com.example.bookinghotel.domain.model.HotelSingleRoom
import com.example.bookinghotel.domain.model.SingleHotel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
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
            val hotel : SingleHotel = it.toSingleHotel()
            it.rooms?.forEach { room -> hotelSingleRoomList.add(HotelSingleRoom(hotel, room)) }
        }
    }

    //funkcja ktora zmienia dostepnosc pokoju na niedostepny -> Done
    //TODO:: przypisuje pokoj do uzytkownika
    //TODO:: przypisuje date wygasniecia pokoju dla uzytkownika
    //TODO:: zaimplementowac Coroutines
    //wersja testowa
    fun reserveRoom(room : HotelSingleRoom) = CoroutineScope(Dispatchers.IO).launch{
        try {
            val result = room.room?.let {
                hotelRoomRepository.hotelCollection()
                    .whereArrayContains("rooms", it)
                    .get()
                    .await()
            }

            result?.forEach { document ->
                val userRoom = room.room
                val docIdRef = hotelRoomRepository.hotelCollection().document(document.id)

                docIdRef.update("rooms", FieldValue.arrayRemove(userRoom)).await()

                userRoom.availability = false
                docIdRef.update("rooms", FieldValue.arrayUnion(userRoom))
            }

            Log.d("SuccesAvaib", "Change success")

        }catch (e : Exception){
            Log.d("FailureAvaib", e.toString())
        }

    }

}