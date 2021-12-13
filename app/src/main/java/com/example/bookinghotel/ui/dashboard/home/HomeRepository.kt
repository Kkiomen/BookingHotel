package com.example.bookinghotel.ui.dashboard.home

import com.example.bookinghotel.domain.models.HotelRoom
import com.example.bookinghotel.domain.repositories.HotelRoomRepository
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Job
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val hotelRoomRepository : HotelRoomRepository
){

    suspend fun findAllHotelRoom(): MutableList<HotelRoom> {
        return hotelRoomRepository.findAllHotelRooms()
    }

}