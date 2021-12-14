package com.example.bookinghotel.domain.repositories

import com.example.bookinghotel.data.models.Hotel
import com.example.bookinghotel.data.models.Room
import com.example.bookinghotel.data.repostiories.HotelRepository
import com.example.bookinghotel.data.repostiories.RoomRepository
import com.example.bookinghotel.data.repostiories.RoomsRepository
import com.example.bookinghotel.domain.models.HotelRoom
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HotelRoomRepository @Inject constructor(
    private val hotelRepository: HotelRepository,
    private val roomsRepository: RoomsRepository,
    private val roomRepository: RoomRepository
) {

    //TODO:: NIE DZIALA DO CHUJA PANA. DO ZROBIENIA
    //this function is responsible for joining 2 models hotel and room into one model which is in Domain
    suspend fun findAllHotelRooms() = withContext(Dispatchers.IO) {

            //getting all hotels Documents reference
            val hotelsId : MutableList<DocumentSnapshot>? = hotelRepository.findAllHotels()?.documents
            val hotels : MutableList<HotelRoom> = mutableListOf()

            hotelsId?.let {
                hotelsId.forEach { hotelReference ->
                    //getting data from hotel model
                    val hotel : Hotel? = hotelRepository.findHotelById(hotelReference.toString())?.toObject()
                    //getting list of Rooms
                    val rooms : MutableList<Room> = mutableListOf()
                    val roomsList : List<String> = roomsRepository.findAllHotelRooms(hotelReference.toString())?.data?.get("hotel_rooms") as List<String>
                    roomsList.forEach{
                        val room = roomRepository.findRoomById(it)?.toObject<Room>()
                        room?.let { it1 -> rooms.add(it1) }
                    }

                    //joining 2 models (hotel, List<Room>) to one HotelRoom
                    val hotelRoom = HotelRoom(hotel?.name, hotel?.city, hotel?.address1, hotel?.address2, hotel?.address3, rooms.toList())
                    hotels.add(hotelRoom)
                }
            }
            //returning List of hotelRoom model
            return@withContext hotels

    }

}