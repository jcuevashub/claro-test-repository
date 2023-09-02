package com.example.clarotest.data.repositories

import com.example.clarotest.util.Resource
import com.example.clarotest.util.UiText
import com.example.clarotest.data.remote.ApiService
import com.example.clarotest.data.remote.model.toListCharacters
import com.example.clarotest.domain.models.EntryDetails
import com.example.clarotest.domain.repositories.EntryRepository
import com.example.clarotest.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AnimalRepositoryImpl @Inject constructor(
    private val api: ApiService
): EntryRepository {
    override suspend fun getEntries(): Flow<Resource<List<EntryDetails>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getAnimals().toListCharacters()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    UiText.StringResource(R.string.error_couldnt_reach_server)
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.oops_something_went_wrong)))
        }
    }



}