package com.example.bookinghotel.data.repostiories

import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HotelRoomRepository {

    private val firestore = Firebase.firestore

    fun findHotelById(documentId : String): DocumentReference {
        return firestore.collection("hotel").document(documentId)
    }

    fun findAllHotels(): CollectionReference {
        return firestore.collection("hotel")
    }

    fun findAllHotelsByCity(chosenCity : String): Query {
        return firestore.collection("hotel").whereEqualTo("city", chosenCity)
    }

    fun addHotel(documentId : Int){
        firestore.collection("hotel").document(documentId.toString())
    }

}