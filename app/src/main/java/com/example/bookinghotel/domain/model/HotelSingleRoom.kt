package com.example.bookinghotel.domain.model

import com.example.bookinghotel.data.models.Room
import java.io.Serializable

/*
* model ktory zawiera wszystkie niezbedne i potrzebne nam dane do wykorzystywania w programie
* jest on stworzony po to zeby nie pobierac wszystkich danych z firebasa (Hotel, Room), bo zabieraly by nam wiele miejsca i wiele z nich bysmy nie wykorzystywali,
* tylko te dane ktore sa nam potrzebne do programu dzieki czemu obiekt zajmuje mniej miejsca i zawiera wszystkie potrzebne nam dane*/

data class HotelSingleRoom (
    val hotel : SingleHotel? = null,
    val room : Room? = null
): Serializable
