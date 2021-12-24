package com.example.bookinghotel.data.models

import java.io.Serializable

data class Room(
    val description : String? = null,
    var availability : Boolean? = null,
    val generated_key : String? = null,
    val number : String? = null,
    val persons : String? = null
) : Serializable