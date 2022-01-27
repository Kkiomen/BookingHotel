package com.example.bookinghotel.domain.services

import android.os.Build
import android.util.Log
import com.example.bookinghotel.data.models.Hotel
import com.example.bookinghotel.data.models.Room
import com.example.bookinghotel.data.models.UserRooms
import com.example.bookinghotel.data.models.toUserReservedRoom
import com.example.bookinghotel.data.repostiories.HotelRepository
import com.example.bookinghotel.data.repostiories.RoomRepository
import com.example.bookinghotel.data.repostiories.UserReservationRepository
import com.example.bookinghotel.domain.model.HotelSingleRoom
import com.example.bookinghotel.domain.model.UserReservedRoom
import com.example.bookinghotel.domain.model.toUserRooms
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/*
*   Logika biznesowa
*   findAllHotelRoom() -> zwraca cala kolekcje hotel i konwertuje na liste obiektow modelu HotelSingleRoom (wykorzystuje ja miedzy innymi HomeFragment)
*   findAllUserRooms() -> to samo co funkcja wyzej tylko zwraca wszystkie zarezerwowane pokoje uzytkownika
*   reserveRoom() -> przy zarezerwowaniu pokoju przez uzyrkowinka:
*       - ustawia dostepnosc pokoju na false w kolekcji hotel -> rooms (firebase) zeby inni uzytkownicy nie mogli go zobaczyc/wynajac
*       - przypisuje pokoj do uzytkownika w kolekcji users_reserved (firebase)
*   passTheReservedRoom() -> po wygasnieciu daty pokoju:
*       - program automatycznie usuwa pokoj z kolekcji users_reserved w firebase uzytkownika
*       - zamienia dostepnosc pokoju na true w kolekcji hotel -> rooms (firebase) tak aby inni uzytkownicy mogli go wynajmywac
 */

class HotelSingleRoomService @Inject constructor(
    private val hotelRepository: HotelRepository,
    private val roomRepository: RoomRepository,
    private val userReservationRepository: UserReservationRepository
) {

    private val user = FirebaseAuth.getInstance().currentUser

    private val userRooms : MutableList<UserRooms> = mutableListOf()

    val hotelSingleRoomList : MutableList<HotelSingleRoom> = mutableListOf()
    var userReservedRoomList : MutableList<UserReservedRoom> = mutableListOf()

    suspend fun findAllHotelRooms() = withContext(Dispatchers.IO) {
        if(hotelSingleRoomList.isNotEmpty())    hotelSingleRoomList.clear()

        val hotelDocuments = hotelRepository.findAll()?.documents

        hotelDocuments?.forEach{ hotelDocument ->
            val hotel = hotelDocument.toObject<Hotel>()
            val roomDocuments = roomRepository.findAll(hotelDocument.id)?.documents

            roomDocuments?.forEach { roomDocument ->
                val room = roomDocument.toObject<Room>()
                hotelSingleRoomList.add(HotelSingleRoom(hotel, room))
            }
        }

        Log.d("hotels", hotelSingleRoomList.toString())
    }

    suspend fun findAllUserRooms() = withContext(Dispatchers.IO){

        if(userRooms.isNotEmpty())              userRooms.clear()
        if(userReservedRoomList.isNotEmpty())   userReservedRoomList.clear()

        val documents = userReservationRepository.findAll()?.documents

        documents?.forEach{
            it.toObject<UserRooms>()?.let { it1 -> userRooms.add(it1) }
        }

        userRooms.forEach{ tempUserRooms -> userReservedRoomList.add(tempUserRooms.toUserReservedRoom()) }

        Log.d("userReservedRooms", userReservedRoomList.toString())
        Log.d("iduser", user?.uid.toString())
    }

    fun reserveRoom(hotelSingleRoom : HotelSingleRoom) = CoroutineScope(Dispatchers.IO).launch{
        try {
            //changing availability value for reserved room
            val userRoom = hotelSingleRoom.room

            val roomsDocumentsResult = roomRepository.findAllMatchingRooms(hotelSingleRoom.hotel!!, userRoom!!)
            val hotelDocumentResult = hotelRepository.findSingleHotel(hotelSingleRoom.hotel)

            userRoom.availability = false

            hotelDocumentResult?.forEach { hotelDocument ->
                roomsDocumentsResult?.forEach { roomDocument ->
                    val docIdRef = roomRepository.findById(hotelDocument.id, roomDocument.id)?.reference
                    docIdRef?.let { docIdRef.set(userRoom) }
                }
            }

            //add room to user reservations
            val customDate = customDate()
            val generateKey = generateKey()

            val userReservedRooms = UserReservedRoom(user?.uid.toString(), hotelSingleRoom.hotel, userRoom, generateKey, customDate).toUserRooms()
            userReservationRepository.add(userReservedRooms)

            Log.d("SuccessAvailability", "Change success")

        }catch (e : Exception){
            Log.d("FailureAvailability", e.toString())
        }
    }

    fun passTheReservedRoom() = CoroutineScope(Dispatchers.IO).launch{
        val currentDate = currentDate()

        try {
            val result = userReservationRepository.outDatedUserReservationRooms(currentDate.toString())

            Log.d("resultSize", result?.size().toString())

            //delete user reserved room
            result?.forEach { document ->
                val userReservationRoom = document.toObject(UserRooms::class.java).toUserReservedRoom()
                val innerResult = roomRepository.findAllMatchingRooms(userReservationRoom.userHotel!!, userReservationRoom.userRoom!!)
                val hotelDocumentResult = hotelRepository.findSingleHotel(userReservationRoom.userHotel)

                userReservationRoom.userRoom.availability = true

                hotelDocumentResult?.forEach { hotelDocument ->
                    innerResult?.forEach { roomDocument ->
                        val docIdRef = roomRepository.findById(hotelDocument.id, roomDocument.id)?.reference
                        docIdRef?.let { docIdRef.set(userReservationRoom.userRoom) }
                    }
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
    * funkcja odpowiedzialna za generowanie klucza
    * */

    private fun generateKey(): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..10).map { i -> kotlin.random.Random.nextInt(0, allowedChars.size) }
            .map(allowedChars::get)
            .joinToString("")
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