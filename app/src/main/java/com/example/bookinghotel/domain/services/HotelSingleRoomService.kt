package com.example.bookinghotel.domain.services

import android.os.Build
import android.util.Log
import com.example.bookinghotel.data.models.Hotel
import com.example.bookinghotel.data.models.UserRooms
import com.example.bookinghotel.data.models.toSingleHotel
import com.example.bookinghotel.data.models.toUserReservedRoom
import com.example.bookinghotel.data.repostiories.HotelRoomRepository
import com.example.bookinghotel.data.repostiories.UserReservationRepository
import com.example.bookinghotel.domain.model.HotelSingleRoom
import com.example.bookinghotel.domain.model.SingleHotel
import com.example.bookinghotel.domain.model.UserReservedRoom
import com.example.bookinghotel.domain.model.toUserRooms
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/*
*   Logika biznesowa
*   findAllHotelRoom() -> zwraca cala kolekcje hotel i konwertuje na liste obiektow modelu HotelSingleRoom (wykorzystuje ja miedzy innymi HomeFragment)
*   reserveRoom() -> przy zarezerwowaniu pokoju przez uzyrkowinka:
*       - ustawia dostepnosc pokoju na false zeby inni uzytkownicy nie mogli go wynajac
*       - przypisuje pokoj do uzytkownika w kolekcji user reserved rooms
*   passTheReservedRoom() -> po wygasnieciu daty pokoju:
*       - program automatycznie usuwa pokoj z reserved rooms uzytkownika
*       - zaminia dostepnosc pokoju na true tak aby inni uzytkownicy mogli go wynajmywac
 */

class HotelSingleRoomService @Inject constructor(
    private val hotelRoomRepository: HotelRoomRepository,
    private val userRoomReservationRepository: UserReservationRepository
) {

    private val user = FirebaseAuth.getInstance().currentUser

    private val hotels : MutableList<Hotel> = mutableListOf()
    val hotelSingleRoomList : MutableList<HotelSingleRoom> = mutableListOf()

    suspend fun findAllHotelRooms() = withContext(Dispatchers.IO) {

        if(hotels.isNotEmpty())                 hotels.clear()
        if(hotelSingleRoomList.isNotEmpty())    hotelSingleRoomList.clear()

        val documents = hotelRoomRepository.findAll()?.documents

        documents?.forEach{
            it.toObject<Hotel>()?.let { it1 -> hotels.add(it1) }
        }

        hotels.forEach{
            val hotel : SingleHotel = it.toSingleHotel()
            it.rooms?.forEach { room -> hotelSingleRoomList.add(HotelSingleRoom(hotel, room)) }
        }
    }

    fun reserveRoom(room : HotelSingleRoom) = CoroutineScope(Dispatchers.IO).launch{
        try {
            //changing availability value for reserved room
            val userRoom = room.room
            val result = room.room?.let { hotelRoomRepository.findAllMatchingRooms(it) }

            result?.forEach { document ->
                val docIdRef = hotelRoomRepository.findHotelDocumentById(document.id)

                docIdRef.update("rooms", FieldValue.arrayRemove(userRoom)).await()

                userRoom?.availability = false
                docIdRef.update("rooms", FieldValue.arrayUnion(userRoom))
            }

            //add room to user reservations
            val customDate = customDate()

            val userReservedRooms = UserReservedRoom(user?.uid.toString(), room.hotel, userRoom, customDate).toUserRooms()
            userRoomReservationRepository.add(userReservedRooms)

            Log.d("SuccessAvailability", "Change success")

        }catch (e : Exception){
            Log.d("FailureAvailability", e.toString())
        }
    }

    fun passTheReservedRoom() = CoroutineScope(Dispatchers.IO).launch{
        val currentDate = currentDate()

        try {
            val result = userRoomReservationRepository.outDatedUserReservationRooms(currentDate.toString())

            //delete user reserved room
            result?.forEach { document ->
                val userRoom = document.toObject(UserRooms::class.java).toUserReservedRoom().userRoom
                val innerResult = userRoom?.let { hotelRoomRepository.findAllMatchingRooms(it) }

                innerResult?.forEach { document ->
                    val docIdRef = hotelRoomRepository.findHotelDocumentById(document.id)

                    docIdRef.update("rooms", FieldValue.arrayRemove(userRoom)).await()

                    userRoom.availability = true
                    docIdRef.update("rooms", FieldValue.arrayUnion(userRoom))
                }

                document.reference.delete()
            }

            withContext(Dispatchers.Main){
                Log.d("successPassingUserRoom", "Passing user room finished success")
            }
        }catch (e : Exception){
            withContext(Dispatchers.Main){
                Log.d("failurePassingUserRoom", e.toString())
            }
        }

    }

    /*
    * funkcje odpowiedzialne za zwracanie obecnej daty
    * oraz generowanie daty do ktorej uzytkownik musi zdac pokoj
    * */

    private fun customDate(): String? {
        //get current date
        val current = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now().plusDays(3)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return dateTools(current)
    }

    private fun currentDate(): String? {
        //get current date
        val current = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return dateTools(current)
    }

    private fun dateTools(current : LocalDateTime) : String? {
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return current.format(formatter)
    }

}