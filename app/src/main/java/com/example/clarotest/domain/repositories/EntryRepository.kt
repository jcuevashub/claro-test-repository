package com.example.clarotest.domain.repositories

import com.example.clarotest.util.Resource
import com.example.clarotest.domain.models.EntryDetails
import kotlinx.coroutines.flow.Flow

interface EntryRepository {
     suspend fun getEntries(): Flow<Resource<List<EntryDetails>>>

}