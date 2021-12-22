package com.example.bookinghotel.data.repostiories

import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class HotelRoomRepository {

    private val firestore = Firebase.firestore
    private val source = Source.CACHE

    suspend fun findHotelById(documentId : String): DocumentSnapshot? {
        return firestore.collection("hotel").document(documentId).get(source).await()
    }

    suspend fun findAllHotels(): QuerySnapshot? {
        return firestore.collection("hotel").get(source).await()
    }

    suspend fun findAllHotelsByCity(chosenCity : String): QuerySnapshot? {
        return firestore.collection("hotel").whereEqualTo("city", chosenCity).get(source).await()
    }

    fun addHotel(documentId : Int): DocumentReference {
        return firestore.collection("hotel").document(documentId.toString())
    }

    fun hotelCollection(): CollectionReference {
        return firestore.collection("hotel")
    }

}