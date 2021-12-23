package com.example.bookinghotel.domain.model

import com.example.bookinghotel.data.models.Room
import java.io.Serializable

data class HotelSingleRoom (
    val hotel : SingleHotel? = null,
    val room : Room? = null
): Serializable
