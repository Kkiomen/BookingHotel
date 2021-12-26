package com.example.bookinghotel.data.repostiories

import com.example.bookinghotel.data.models.Room
import com.example.bookinghotel.data.models.UserRooms
import com.example.bookinghotel.domain.model.UserReservedRoom
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

/*
* Repozytorium dla modelu UserRooms
* jest odpowiedzialna za operacje na tej kolekcji
* */

class UserReservationRepository : DaoRepository{

    private val collection = Firebase.firestore.collection("users_reservations")

    override suspend fun findById(documentId : String): DocumentSnapshot? {
        return collection.document(documentId).get().await()
    }

    override suspend fun findAll(): QuerySnapshot? {
        return collection.get().await()
    }

    suspend fun outDatedUserReservationRooms(currentDate : String): QuerySnapshot? {
        return collection
            .whereLessThanOrEqualTo("expirationDate", currentDate)
            .get()
            .await()
    }

    fun add(userRooms: UserRooms) {
        //collection.document(documentId).update("user_rooms", FieldValue.arrayUnion(userRooms))
        collection.add(userRooms)
    }

}