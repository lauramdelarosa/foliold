package com.delarosa.folio.home.presentation.server

import com.delarosa.folio.core.constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DogService {

    @GET("/v1/breeds")
    suspend fun getDogList(
        @Query(KEY) apiKey: String = API_KEY
    ): Response<List<DogResponse>>
}

private const val KEY = "x-api_key"
