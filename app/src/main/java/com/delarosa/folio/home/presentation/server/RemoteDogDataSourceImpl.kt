package com.delarosa.folio.home.presentation.server

import com.delarosa.folio.core.presentation.callServices
import com.delarosa.folio.core.presentation.safeApiCall
import com.delarosa.folio.home.data.source.RemoteDogDatasource
import com.delarosa.folio.home.domain.entities.Dog
import javax.inject.Inject

class RemoteDogDataSourceImpl @Inject constructor(private val dogService: DogService) : RemoteDogDatasource {

    override suspend fun getDogList(): Result<List<Dog>> =
        safeApiCall(
            call = {
                dogService.getDogList().callServices().fold(
                    onSuccess = { data ->
                        Result.success(data.map { it.toDog() })
                    },
                    onFailure = {
                        Result.failure(it)
                    }
                )
            },
            errorMessage = "Something failed calling service '../getDogList'"
        )
}
