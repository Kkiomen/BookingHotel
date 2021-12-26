package com.example.bookinghotel.backgroundServices

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.bookinghotel.data.repostiories.HotelRoomRepository
import com.example.bookinghotel.data.repostiories.UserReservationRepository
import com.example.bookinghotel.domain.services.HotelSingleRoomService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Scheduler
import javax.inject.Inject

/*
* Klasa odpowiedzialna za sprawdzanie daty zarezerwowanego pokoju
* i jesli jest przedawniona usuwa pokoj z zarezerwowanych pokoi uzytkownika
* system wywoluje funkcje 1 raz dziennie
* */

class CheckDateService(
    appContext: Context,
    workerParams: WorkerParameters)
    : Worker(appContext, workerParams) {

    private val hotelSingleRoomService: HotelSingleRoomService = HotelSingleRoomService(HotelRoomRepository(), UserReservationRepository())

    override fun doWork(): Result {
        hotelSingleRoomService.passTheReservedRoom()
        Log.d("workManagerInvoke", "work Manager Invoke")
        return Result.success();
    }

}
