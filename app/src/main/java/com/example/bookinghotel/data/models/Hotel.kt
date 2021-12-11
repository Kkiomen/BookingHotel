package com.example.bookinghotel.data.models

import com.example.bookinghotel.domain.models.HotelRoom

data class Hotel(
    val uid: Int? = null,
    val name: String? = null,
    val address1: String? = null,
    val address2: String? = null,
    val address3: String? = null,
    val city: String? = null,
    val rooms : List<Int>? = null

    //val postcode: String? = null,
    //val country: String? = null,
    //val description: String? = null,
)

//Work in progress
fun Hotel.toHotelRoom(currentRoom : List<Room>) : HotelRoom{

    var tempRooms : MutableList<Room> = mutableListOf()

    rooms?.let { _rooms ->
            _rooms.forEach { id ->
                //na poczekaniu do naprawy
                var room : Room? = null

                currentRoom.stream()
                    .filter{ it.uid == id }
                    .findFirst()
                    .ifPresent{ r -> {room = r} }

                room?.let { tempRooms.add(it) }
        }
    }

    return HotelRoom(
        uid = uid,
        name = name,
        address1 = address1,
        address2 = address2,
        address3 = address3,
        city = city,
        rooms = tempRooms.toList()
    )
}