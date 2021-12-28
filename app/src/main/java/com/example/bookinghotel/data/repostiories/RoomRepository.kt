package com.example.bookinghotel.data.repostiories

import com.example.bookinghotel.data.models.Hotel
import com.example.bookinghotel.data.models.Room
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

/*
* Repozytorium dla modelu Room
* Repozytorium dziedziczy po HotelRepository
* jest odpowiedzialna za operacje na tej kolekcji
* */

class RoomRepository : HotelRepository() {

    suspend fun findById(hotelDocument: String, roomDocument : String): DocumentSnapshot? {
        return collection
            .document(hotelDocument)
            .collection("rooms")
            .document(roomDocument)
            .get()
            .await()
    }

    suspend fun findAll(hotelDocument: String): QuerySnapshot? {
        return collection
            .document(hotelDocument)
            .collection("rooms")
            .get()
            .await()
    }

    suspend fun findAllMatchingRooms(hotel: Hotel, room : Room): MutableList<DocumentSnapshot>? {
        var returnResult: MutableList<DocumentSnapshot>? = null
        val result = findSingleHotel(hotel)

        result?.documents?.forEach{
            returnResult = it.reference.collection("rooms")
                .whereEqualTo("description", room.description)
                .whereEqualTo("availability", room.availability)
                .whereEqualTo("number", room.number)
                .whereEqualTo("persons", room.persons)
                .get()
                .await()
                .documents
        }

        return  returnResult
    }

    fun add(documentId : String, room : Room) {
        collection.document(documentId).collection("rooms").add(room)
    }

}