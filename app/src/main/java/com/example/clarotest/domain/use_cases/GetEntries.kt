package com.example.clarotest.domain.use_cases

import com.example.clarotest.common.Resource
import com.example.clarotest.domain.models.EntryDetails
import com.example.clarotest.domain.repositories.AnimalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEntriesUseCase @Inject constructor(private val repository: AnimalRepository)
{
     suspend operator fun invoke(): Flow<Resource<List<EntryDetails>>> {
        return repository.getEntries()
    }
}