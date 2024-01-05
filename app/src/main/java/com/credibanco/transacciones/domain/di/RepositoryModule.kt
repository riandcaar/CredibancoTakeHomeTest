package com.credibanco.transacciones.domain.di

import com.credibanco.transacciones.data.repositories.MainRepositoryImpl
import com.credibanco.transacciones.domain.MainRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsMainRepository(
        mainRepository: MainRepositoryImpl
    ): MainRepositoryInterface

}