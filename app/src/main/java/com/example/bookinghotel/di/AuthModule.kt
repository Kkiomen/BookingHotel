package com.example.bookinghotel.di

import com.example.bookinghotel.ui.dashboard.home.HomeRepository
import com.example.bookinghotel.ui.login.LoginRepository
import com.example.bookinghotel.ui.register.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
* Wstrzykiwanie repozytoriów, ktore podlaczone sa do aktywnosci
* koter zajmuja sie autoryzacja, czyli np. logowanie, rejestracja*/

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Singleton
    @Provides
    fun provideLoginRepository() : LoginRepository = LoginRepository()

    @Singleton
    @Provides
    fun provideRegisterRepository() : RegisterRepository = RegisterRepository()

}