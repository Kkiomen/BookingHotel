package com.example.bookinghotel.data.repostiories

import com.example.bookinghotel.data.models.Hotel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class HotelRepository {

    private val firestore = Firebase.firestore
    private val source = Source.CACHE

    suspend fun findHotelById(documentId : String): DocumentSnapshot? {
        return firestore.collection("hotel").document(documentId).get(source).await()
    }
    suspend fun findAllHotels(): QuerySnapshot? {
        return firestore.collection("hotel").get(source).await()
    }
    suspend fun findAllHotelsByCity(chosenCity : String){
        firestore.collection("hotel").whereEqualTo("city", chosenCity).get(source).await()
    }

    fun addHotel(documentId : Int){
        firestore.collection("hotel").document(documentId.toString())
    }

}