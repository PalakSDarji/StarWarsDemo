package com.palak.starwarsdemo.repo

import com.palak.starwarsdemo.api.ApiInterface
import com.palak.starwarsdemo.models.CharacterListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class CharacterRepo @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getCharactersList() : CharacterListResponse{

        return withContext(Dispatchers.IO){

            val response : CharacterListResponse = apiInterface.getCharacters(1)

            Timber.d("CharacterListResponse fetched : $response")

            response
        }
    }
}