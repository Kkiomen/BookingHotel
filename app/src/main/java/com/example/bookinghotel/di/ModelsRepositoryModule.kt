package com.example.bookinghotel.di

import com.example.bookinghotel.data.repostiories.HotelRepository
import com.example.bookinghotel.data.repostiories.RoomRepository
import com.example.bookinghotel.data.repostiories.UserReservationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
* Wstrzykiwanie repozytoriow odpowiedzialnych za operacje na surowych modelach (package data.repositories)
* */

@Module
@InstallIn(SingletonComponent::class)
class ModelsRepositoryModule {

    @Singleton
    @Provides
    fun provideHotelRepository() : HotelRepository = HotelRepository()

    @Singleton
    @Provides
    fun provideUserReservationRoomRepository() : UserReservationRepository = UserReservationRepository()

    @Singleton
    @Provides
    fun provideRoomRepository() : RoomRepository = RoomRepository()

}