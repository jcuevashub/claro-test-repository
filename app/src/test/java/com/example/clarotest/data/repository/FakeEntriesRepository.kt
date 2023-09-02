package com.example.clarotest.data.repository

import com.example.clarotest.util.Resource
import com.example.clarotest.domain.models.EntryDetails
import com.example.clarotest.domain.repositories.EntryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeEntriesRepository : EntryRepository {
    private val entries = mutableListOf(
        EntryDetails("AdoptAPet", "Resource to help get pets adopted", "apiKey", true, "yes", "https://www.adoptapet.com/public/apis/pet_list.html", "Animals"),
        EntryDetails("Axolotl", "Collection of axolotl pictures and facts", "", true, "no", "https://theaxolotlapi.netlify.app/", "Animals"))

    override suspend fun getEntries(): Flow<Resource<List<EntryDetails>>> {
        return flow { emit(Resource.Success(entries)) }
    }

}