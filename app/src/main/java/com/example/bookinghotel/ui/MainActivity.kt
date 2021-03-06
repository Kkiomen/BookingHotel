package com.example.bookinghotel.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bookinghotel.R
import com.example.bookinghotel.data.models.Hotel
import com.example.bookinghotel.data.models.Room
import com.example.bookinghotel.ui.login.LoginFragment
import com.example.bookinghotel.ui.register.RegisterFragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

/*
* aktywnosc od ktorej startuje aplikacja
* TODO:: zaimplementowac zeby przed ta aktywnoscia byl jeszcze splash screen
*  */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val rooms : MutableList<Room> = mutableListOf()
//        rooms.add(Room("Najpiekniejszy widok ugulem", true, "14", "2"))
//        rooms.add(Room("pierdole wasze matki", true, "17", "3"))
//        rooms.add(Room("Zydzi do pieca", true, "24", "4"))
//
//        val hotel : Hotel = Hotel("Mariott", "Wojska 22", "23-224", "Warsaw")
//
//        Firebase.firestore.collection("hotel").add(hotel)
//            .addOnSuccessListener {
//                rooms.forEach{ room ->
//                    it.collection("rooms").add(room)
//                }
//                Log.d("success", "success")
//            }
//            .addOnFailureListener { Log.d("failuure", "failure") }

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.login_panel, LoginFragment())
                //.replace(R.id.register_panel, RegisterFragment())
                .commit()
        }
    }
}