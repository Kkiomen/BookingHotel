package com.example.bookinghotel.ui.dashboard.home

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class HomeRepository {
    private val db = Firebase.firestore
    private val source = Source.CACHE

    //Async Function get Hotels from firestore collection named hotel
    suspend fun getHotels(): QuerySnapshot = db.collection("hotel").get(source).await()

    //Async function get Rooms from firestore collection named room
    suspend fun getRooms(): QuerySnapshot = db.collection("room").get(source).await()
}