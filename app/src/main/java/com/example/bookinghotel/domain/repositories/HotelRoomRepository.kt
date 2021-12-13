package com.example.bookinghotel.domain.repositories

import com.example.bookinghotel.data.models.Hotel
import com.example.bookinghotel.data.models.Room
import com.example.bookinghotel.data.repostiories.HotelRepository
import com.example.bookinghotel.data.repostiories.RoomRepository
import com.example.bookinghotel.data.repostiories.RoomsRepository
import com.example.bookinghotel.domain.models.HotelRoom
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HotelRoomRepository @Inject constructor(
    private val hotelRepository: HotelRepository,
    private val roomsRepository: RoomsRepository,
    private val roomRepository: RoomRepository
) {
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
                    roomsRepository.findAllHotelRooms(hotelReference.reference.toString())?.data?.values?.forEach{
                        roomRepository.findRoomById(it.toString())?.toObject<Room>()?.let { it1 -> rooms.add(it1) }
                    }
                    //joining 2 models (hotel, List<Room>) to one HotelRoom
                    val hotelRoom : HotelRoom = HotelRoom(hotel?.name, hotel?.city, hotel?.address1, hotel?.address2, hotel?.address3, rooms.toList())
                    hotels.add(hotelRoom)
                }
            }
            //returning List of hotelRoom model
            return@withContext hotels

    }

}