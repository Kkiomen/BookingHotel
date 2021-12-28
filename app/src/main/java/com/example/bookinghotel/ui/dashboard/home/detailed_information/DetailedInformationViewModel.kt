package com.example.bookinghotel.ui.dashboard.home.detailed_information

import androidx.lifecycle.ViewModel
import com.example.bookinghotel.domain.model.HotelSingleRoom
import com.example.bookinghotel.ui.dashboard.reservation.ReservationViewModel
import com.example.bookinghotel.ui.login.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*
* Przyjmuje jako parametr repozytorium
* udostepnia dla aktywnosci pola reservationState ktore zwraca czy funkcja wykonala sie poprawnie czy nie
* hotelRoom czyli pokoj ktory jest mozliwy do wynajecia
* oraz metode roomReservaiton() -> ktora jest odpowiedzialna za wynajecie pokoju przez uzytkownika
* wszytkie te pola i metoda sa wykorzystywane w aktywnosci
* */

@HiltViewModel
class DetailedInformationViewModel @Inject constructor(
    private val detailedinformationRepository: DetailedinformationRepository
) : ViewModel(){

    sealed class ReservationState(){
        object Empty : ReservationState()
        object Success : ReservationState()
        data class Error(val message : String) : ReservationState()
    }

    private val _reservationState = MutableStateFlow<ReservationState>(ReservationState.Empty)
    val reservationState : StateFlow<ReservationState> = _reservationState

    var hotelRoom : HotelSingleRoom? = null

    fun roomReservation() = CoroutineScope(Dispatchers.IO).launch{
        _reservationState.value = ReservationState.Empty
        try {
            hotelRoom?.let { detailedinformationRepository.roomReservation(it) }

            withContext(Dispatchers.Main){
                _reservationState.value = ReservationState.Success
            }
        }catch (e : Exception){
            withContext(Dispatchers.Main){
                _reservationState.value = ReservationState.Error(e.toString())
            }
        }
    }

}