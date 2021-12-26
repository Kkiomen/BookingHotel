package com.example.bookinghotel.data.models

import java.io.Serializable

/*
* Surowy model dla danych z firebase
* */

data class Hotel(
    val name: String? = null,
    val address1: String? = null,
    val address2: String? = null,
    val address3: String? = null
) : Serializable