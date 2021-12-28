package com.example.bookinghotel

import android.app.Application
import androidx.hilt.work.HiltWorker
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.PeriodicWorkRequest
import com.example.bookinghotel.backgroundServices.CheckDateService
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/*
* Klasa definiujaca ze w tej aplikacji bedzie uzywane wstrzykiwanie zaleznosci
* wstrzykiwanie zaleznosci znajduje sie w folderze di (stworzone przez programiste repozytoria, serwisy itd.)
* @HiltAndroidApp -> glowna klasa hilta, bez niej wstrzykiwanie nie dziala
* @AndroidEntryApp -> dajemy nad fragmentami i aktywnosciami, zeby mozna bylo do nich wstrzykiwac viewModele itp.
* @HiltViewModel -> Adnotacja dla view modelu dzieki czemu vieModel jest automatycznie wstrzykiwany do aktywnosci za pomoca vieModels()
* @Inject constructor(...) -> jest odpowiedzialny za wstrzykiwanie do klasy klas zdefiniowanych w klasach z folderu di
* */

@HiltAndroidApp
class RoomBookingApplication : Application(){
}