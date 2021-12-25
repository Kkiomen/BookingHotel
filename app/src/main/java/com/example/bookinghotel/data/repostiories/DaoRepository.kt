package com.example.bookinghotel.data.repostiories

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

interface DaoRepository {
    suspend fun findById(documentId : String): DocumentSnapshot?
    suspend fun findAll(): QuerySnapshot?
}