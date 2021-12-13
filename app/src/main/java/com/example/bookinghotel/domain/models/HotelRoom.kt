package com.example.bookinghotel.domain.models

import com.example.bookinghotel.data.models.Room

data class HotelRoom(
    val name: String? = null,
    val city: String? = null,
    val address1: String? = null,
    val address2: String? = null,
    val address3: String? = null,
    val rooms: List<Room>? = null
)


