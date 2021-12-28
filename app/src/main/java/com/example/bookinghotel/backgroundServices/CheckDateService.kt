package com.example.bookinghotel.backgroundServices

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.bookinghotel.data.repostiories.HotelRepository
import com.example.bookinghotel.data.repostiories.RoomRepository
import com.example.bookinghotel.data.repostiories.UserReservationRepository
import com.example.bookinghotel.domain.services.HotelSingleRoomService

/*
* Klasa odpowiedzialna za sprawdzanie daty zarezerwowanego pokoju
* i jesli jest przedawniona usuwa pokoj z zarezerwowanych pokoi uzytkownika
* system wywoluje funkcje 1 raz dziennie
* */

class CheckDateService(
    appContext: Context,
    workerParams: WorkerParameters)
    : Worker(appContext, workerParams) {

    private val hotelSingleRoomService: HotelSingleRoomService = HotelSingleRoomService(HotelRepository(), RoomRepository(), UserReservationRepository())

    override fun doWork(): Result {
        hotelSingleRoomService.passTheReservedRoom()
        Log.d("workManagerInvoke", "work Manager Invoke")
        return Result.success();
    }

}
