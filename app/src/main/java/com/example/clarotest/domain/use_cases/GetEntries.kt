package com.example.clarotest.domain.use_cases

import com.example.clarotest.util.Resource
import com.example.clarotest.domain.models.EntryDetails
import com.example.clarotest.domain.repositories.EntryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEntriesUseCase @Inject constructor(private val repository: EntryRepository)
{
     suspend operator fun invoke(): Flow<Resource<List<EntryDetails>>> {
        return repository.getEntries()
    }
}