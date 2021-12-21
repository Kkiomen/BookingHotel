package com.example.bookinghotel.data.models

data class User(
    val email : String? = null,
    val password : String? = null,
    val hotelId : String? = null,
    val rooms : List<Room>? = null
)
