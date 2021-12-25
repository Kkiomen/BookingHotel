package com.example.bookinghotel.domain.model

import com.example.bookinghotel.data.models.Room
import com.example.bookinghotel.data.models.UserRooms

data class UserReservedRoom(
    val userId : String? = null,
    val userHotel: SingleHotel? = null,
    val userRoom: Room? = null,
    val expirationDate: String? = null,
    //val generateKeyField : String? = null
)

fun UserReservedRoom.toUserRooms() : UserRooms = UserRooms(
    userId = userId,
    name = userHotel?.name,
    address1 = userHotel?.address1,
    address2 = userHotel?.address2,
    address3 = userHotel?.address3,
    description = userRoom?.description,
    availability = userRoom?.availability,
    generated_key = userRoom?.generated_key,
    number = userRoom?.number,
    persons = userRoom?.persons,
    expirationDate = expirationDate
)