package com.example.bookinghotel.data.models

data class Room(
    val generatedKey : String? = null,
    val number: String? = null,
    val persons : String? = null,
    val availability: Boolean = false,
    //val description: String? = null,
)