package com.example.bookinghotel.data.repostiories

import com.example.bookinghotel.data.models.Room
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class HotelRoomRepository : DaoRepository{

    private val collection = Firebase.firestore.collection("hotel")
    private val source = Source.CACHE

    override suspend fun findById(documentId : String): DocumentSnapshot? {
        return collection.document(documentId).get(source).await()
    }

    override suspend fun findAll(): QuerySnapshot? {
        return collection.get(source).await()
    }

    suspend fun findAllHotelsByCity(chosenCity : String): QuerySnapshot? {
        return collection
            .whereEqualTo("city", chosenCity)
            .get(source)
            .await()
    }

    suspend fun findAllMatchingRooms(room : Room): QuerySnapshot? {
        return collection
            .whereArrayContains("rooms", room)
            .get()
            .await()
    }

    fun add(documentId : Int): DocumentReference {
        return collection.document(documentId.toString())
    }

    fun findHotelDocumentById(documentId: String): DocumentReference {
        return collection.document(documentId)
    }

}