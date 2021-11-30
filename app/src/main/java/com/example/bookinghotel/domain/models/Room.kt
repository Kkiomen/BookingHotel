package com.example.bookinghotel.domain.models

data class Room(
    val uid: Int? = null,
    val idHotel: Int? = null,
    val availability: Boolean = false,
    val name: String? = null,
    val description: String? = null,
    val countBed: Int? = null,
)