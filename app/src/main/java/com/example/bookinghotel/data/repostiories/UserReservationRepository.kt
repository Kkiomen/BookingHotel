package com.example.bookinghotel.data.repostiories

import com.example.bookinghotel.data.models.UserRooms
import com.example.bookinghotel.domain.model.UserReservedRoom
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserReservationRepository : DaoRepository{

    private val collection = Firebase.firestore.collection("users_reservations")
    private val source = Source.CACHE

    override suspend fun findById(documentId : String): DocumentSnapshot? {
        return collection.document(documentId).get(source).await()
    }

    override suspend fun findAll(): QuerySnapshot? {
        return collection.get(source).await()
    }

    fun userReservationCollection(): CollectionReference {
        return collection
    }

    fun add(userRooms: UserRooms) {
        //collection.document(documentId).update("user_rooms", FieldValue.arrayUnion(userRooms))
        collection.add(userRooms)
    }

}