package com.example.bookinghotel.domain.model

import java.io.Serializable

data class SingleHotel (
    val name: String? = null,
    val address1: String? = null,
    val address2: String? = null,
    val address3: String? = null
) : Serializable