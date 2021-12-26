package com.example.bookinghotel.data.models

import com.example.bookinghotel.domain.model.SingleHotel
import com.example.bookinghotel.domain.model.UserReservedRoom

/*
* Surowy model dla danych z firebase
* */

data class UserRooms(
    val userId : String? = null,
    val name: String? = null,
    val address1: String? = null,
    val address2: String? = null,
    val address3: String? = null,
    val description : String? = null,
    var availability : Boolean? = null,
    val generated_key : String? = null,
    val number : String? = null,
    val persons : String? = null,
    val expirationDate: String? = null
)

fun UserRooms.toUserReservedRoom() : UserReservedRoom = UserReservedRoom(
    userId = userId,
    userHotel = SingleHotel(name, address1, address2, address3),
    userRoom = Room(description, availability, number, persons),
    generated_key = generated_key,
    expirationDate = expirationDate
)
