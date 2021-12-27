package com.example.bookinghotel.data.models

/*
* Surowy model dla danych z firebase
* //TODO:: opisac UserRepository dla tego modelu
* */

data class User(
    val email : String? = null,
    val password : String? = null,
    val hotelId : String? = null,
    val rooms : List<Room>? = null
)
