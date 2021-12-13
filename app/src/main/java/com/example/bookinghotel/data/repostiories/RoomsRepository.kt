package com.example.bookinghotel.data.repostiories

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class RoomsRepository {

    private val firestore = Firebase.firestore
    private val source = Source.CACHE

    suspend fun findAllHotelRooms(hotelDocumentId: String) : DocumentSnapshot? {
        return firestore.collection("rooms").document(hotelDocumentId).get(source).await()
    }

}