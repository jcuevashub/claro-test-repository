package com.example.clarotest.data.remote
import com.example.clarotest.data.remote.model.EntryDto
import retrofit2.http.GET

interface ApiService {
    @GET("entries")
    suspend fun getAnimals(): EntryDto
}