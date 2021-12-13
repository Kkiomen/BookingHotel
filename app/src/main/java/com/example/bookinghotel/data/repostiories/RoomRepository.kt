package com.example.bookinghotel.data.repostiories

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class RoomRepository {

    private val firestore = Firebase.firestore
    private val source = Source.CACHE

    suspend fun findRoomById(hotelDocumentId : String): DocumentSnapshot? {
        return firestore.collection("room").document(hotelDocumentId).get(source).await()
    }
    suspend fun findAllRooms(): QuerySnapshot? {
        return firestore.collection("room").get(source).await()
    }

    fun addRoom(documentId : Int){
        firestore.collection("room").document(documentId.toString())
    }

}