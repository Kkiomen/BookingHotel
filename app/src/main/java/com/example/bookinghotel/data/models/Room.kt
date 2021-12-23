package com.example.bookinghotel.data.models

import java.io.Serializable

data class Room(
    val description : String? = null,
    val availability : Boolean? = null,
    val generated_key : String? = null,
    val number : String? = null,
    val persons : String? = null
) : Serializable
