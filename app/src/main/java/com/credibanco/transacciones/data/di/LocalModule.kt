package com.credibanco.transacciones.data.di

import android.content.Context
import androidx.room.Room
import com.credibanco.transacciones.data.local.CredibancoDatabase
import com.credibanco.transacciones.data.local.dao.TransactionsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): CredibancoDatabase {

        return Room.databaseBuilder(context, CredibancoDatabase::class.java, "mydb")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAgentDao(database: CredibancoDatabase): TransactionsDao {

        return database.transactionsDao()
    }
}