package com.example.bookinghotel.data.models

data class Hotel(
    val name: String? = null,
    val address1: String? = null,
    val address2: String? = null,
    val address3: String? = null,
    val rooms : List<Room>? = null
)