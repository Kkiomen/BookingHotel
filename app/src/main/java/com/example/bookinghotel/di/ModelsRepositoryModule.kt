package com.example.bookinghotel.di

import com.example.bookinghotel.data.repostiories.HotelRoomRepository
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
    fun provideRoomRepository() : HotelRoomRepository = HotelRoomRepository()

    @Singleton
    @Provides
    fun provideUserReservationRoomRepository() : UserReservationRepository = UserReservationRepository()

}