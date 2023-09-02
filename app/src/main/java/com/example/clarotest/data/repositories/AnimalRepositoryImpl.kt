package com.example.clarotest.data.repositories

import com.example.clarotest.common.Resource
import com.example.clarotest.common.UiText
import com.example.clarotest.data.remote.ApiService
import com.example.clarotest.data.remote.model.toListCharacters
import com.example.clarotest.domain.models.EntryDetails
import com.example.clarotest.domain.repositories.AnimalRepository
import com.example.clarotest.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AnimalRepositoryImpl @Inject constructor(
    private val api: ApiService
): AnimalRepository {

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