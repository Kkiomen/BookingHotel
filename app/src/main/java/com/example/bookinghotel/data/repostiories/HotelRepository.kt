package com.example.bookinghotel.data.repostiories

import com.example.bookinghotel.data.models.Hotel
import com.example.bookinghotel.data.models.Room
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

/*
* Repozytorium dla modeli Hotel i Room
* jest odpowiedzialna za operacje na tych kolekcjach
* */

open class HotelRepository : DaoRepository{

    protected open val collection = Firebase.firestore.collection("hotel")

    override suspend fun findById(documentId : String): DocumentSnapshot? {
        return collection.document(documentId).get().await()
    }

    override suspend fun findAll(): QuerySnapshot? {
        return collection
            .get()
            .await()
    }

    suspend fun findSingleHotel(hotel: Hotel): QuerySnapshot? {
        return collection
            .whereEqualTo("name", hotel.name)
            .whereEqualTo("address1", hotel.address1)
            .whereEqualTo("address2", hotel.address2)
            .whereEqualTo("address3", hotel.address3)
            .get()
            .await()
    }

    suspend fun findAllHotelsByCity(chosenCity : String): QuerySnapshot? {
        return collection
            .whereEqualTo("city", chosenCity)
            .get()
            .await()
    }

    open fun add(hotel : Hotel) {
        collection.add(hotel)
    }

}