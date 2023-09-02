package com.example.clarotest.domain.repositories

import com.example.clarotest.common.Resource
import com.example.clarotest.domain.models.EntryDetails
import kotlinx.coroutines.flow.Flow

interface AnimalRepository {
     suspend fun getEntries(): Flow<Resource<List<EntryDetails>>>

}