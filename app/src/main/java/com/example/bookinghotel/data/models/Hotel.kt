package com.example.bookinghotel.data.models

import com.example.bookinghotel.domain.model.SingleHotel

/*
* Surowy model dla danych z firebase
* */

data class Hotel(
    val name: String? = null,
    val address1: String? = null,
    val address2: String? = null,
    val address3: String? = null,
    val rooms : List<Room>? = null
)

fun Hotel.toSingleHotel() : SingleHotel = SingleHotel(
    name = name,
    address1 = address1,
    address2 = address2,
    address3 = address3
)