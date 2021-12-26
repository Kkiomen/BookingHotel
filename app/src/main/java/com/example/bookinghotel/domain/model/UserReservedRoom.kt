package com.example.bookinghotel.domain.model

import com.example.bookinghotel.data.models.Hotel
import com.example.bookinghotel.data.models.Room
import com.example.bookinghotel.data.models.UserRooms
import java.io.Serializable

/*
* klasa zawierajaca wszystkie dane ktore potrzbuje uzytkownik przy zarezerwowaniu pokoju
* */

data class UserReservedRoom(
    val userId : String? = null,
    val userHotel: Hotel? = null,
    val userRoom: Room? = null,
    val generated_key : String? = null,
    val expirationDate: String? = null
) : Serializable

fun UserReservedRoom.toUserRooms() : UserRooms = UserRooms(
    userId = userId,
    name = userHotel?.name,
    address1 = userHotel?.address1,
    address2 = userHotel?.address2,
    address3 = userHotel?.address3,
    description = userRoom?.description,
    availability = userRoom?.availability,
    number = userRoom?.number,
    persons = userRoom?.persons,
    generated_key = generated_key,
    expirationDate = expirationDate
)