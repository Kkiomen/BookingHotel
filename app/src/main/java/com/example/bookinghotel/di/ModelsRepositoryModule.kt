package com.example.bookinghotel.di

import com.example.bookinghotel.data.repostiories.HotelRepository
import com.example.bookinghotel.data.repostiories.RoomRepository
import com.example.bookinghotel.data.repostiories.RoomsRepository
import com.example.bookinghotel.domain.repositories.HotelRoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModelsRepositoryModule {

    @Singleton
    @Provides
    fun provideHotelRepository() : HotelRepository = HotelRepository()

    @Singleton
    @Provides
    fun provideRoomRepository() : RoomRepository = RoomRepository()

    @Singleton
    @Provides
    fun provideRoomsRepository() : RoomsRepository = RoomsRepository()

    @Singleton
    @Provides
    fun provideHotelRoomRepository(hotelRepository: HotelRepository, roomRepository: RoomRepository, roomsRepository: RoomsRepository) : HotelRoomRepository =
        HotelRoomRepository(hotelRepository, roomsRepository, roomRepository)

}