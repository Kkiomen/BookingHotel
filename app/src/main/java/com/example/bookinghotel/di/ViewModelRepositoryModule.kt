package com.example.bookinghotel.di

import com.example.bookinghotel.domain.services.HotelSingleRoomService
import com.example.bookinghotel.ui.dashboard.home.HomeRepository
import com.example.bookinghotel.ui.dashboard.home.detailed_information.DetailedinformationRepository
import com.example.bookinghotel.ui.dashboard.reservation.ReservationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
* Wstrzykiwanie repozytoriow ktore potrzebne sa w viewModelu
* */

@Module
@InstallIn(SingletonComponent::class)
class ViewModelRepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(hotelSingleRoomService: HotelSingleRoomService) : HomeRepository = HomeRepository(hotelSingleRoomService)

    @Singleton
    @Provides
    fun provideDetailedinformationRepository(hotelSingleRoomService: HotelSingleRoomService) : DetailedinformationRepository = DetailedinformationRepository(hotelSingleRoomService)

    @Singleton
    @Provides
    fun provideReservationRepository(hotelSingleRoomService: HotelSingleRoomService) : ReservationRepository = ReservationRepository(hotelSingleRoomService)


}