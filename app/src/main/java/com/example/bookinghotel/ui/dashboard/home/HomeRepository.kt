package com.example.bookinghotel.ui.dashboard.home

import com.example.bookinghotel.data.repostiories.HotelRoomRepository
import com.example.bookinghotel.domain.services.HotelSingleRoomService
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import kotlinx.coroutines.Job
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val hotelSingleRoomService: HotelSingleRoomService
){

    val hotelSingleRoom = hotelSingleRoomService.hotelSingleRoomList

    suspend fun findAllHotelsWithRooms() {
        hotelSingleRoomService.findAllHotelRooms()
    }


}