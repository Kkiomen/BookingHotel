package com.example.bookinghotel.domain.model

import java.io.Serializable

/*
* jest to Klasa Hotelu ktory zostal uproszczony o usuniecie listy z pokojami bo nie sa nam potrzebne
*/

data class SingleHotel (
    val name: String? = null,
    val address1: String? = null,
    val address2: String? = null,
    val address3: String? = null
) : Serializable