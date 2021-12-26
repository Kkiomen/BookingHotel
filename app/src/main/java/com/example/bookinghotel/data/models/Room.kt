package com.example.bookinghotel.data.models

import java.io.Serializable

/*
* Surowy model dla danych z firebase
* */

data class Room(
    val description : String? = null,
    var availability : Boolean? = null,
    val number : String? = null,
    val persons : String? = null
) : Serializable