package com.palak.starwarsdemo.api

import com.palak.starwarsdemo.models.CharacterListResponse
import com.palak.starwarsdemo.utils.Constants
import retrofit2.http.*

/**
 * Api Contracts
 */
@JvmSuppressWildcards
interface ApiInterface {

    @GET(Constants.GET_CHARACTERS)
    suspend fun getCharacters(
        @Query("page") pageNo: Int?
    ): CharacterListResponse?
}

