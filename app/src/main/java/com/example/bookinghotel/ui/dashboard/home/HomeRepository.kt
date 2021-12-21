package com.example.bookinghotel.ui.dashboard.home

import com.example.bookinghotel.data.repostiories.HotelRoomRepository
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val hotelRoomRepository : HotelRoomRepository
){

    private val source = Source.CACHE

    suspend fun findAllHotelsWithRooms(): QuerySnapshot? {
        return hotelRoomRepository.findAllHotels().get(source).await()
    }

    suspend fun findHotelWithRoomsById(documentId : String) {
        hotelRoomRepository.findHotelById(documentId).get(source).await()
    }

    suspend fun findHotelsWithRoomByCity(city : String){
        hotelRoomRepository.findAllHotelsByCity(city).get(source).await()
    }

}