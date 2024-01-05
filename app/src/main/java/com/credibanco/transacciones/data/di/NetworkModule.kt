package com.credibanco.transacciones.data.di

import com.credibanco.transacciones.data.network.TransactionsApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val BASEURL = "http://10.0.2.2:8080/api/payments/"

    @Provides
    @Singleton
    fun provideRetrofit(): TransactionsApi {

        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
            .create(TransactionsApi::class.java)
    }
}