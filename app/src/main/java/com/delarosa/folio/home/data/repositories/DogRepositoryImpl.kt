package com.delarosa.folio.home.data.repositories

import com.delarosa.folio.home.data.source.RemoteDogDatasource
import com.delarosa.folio.home.domain.entities.Dog
import com.delarosa.folio.home.domain.repositories.DogRepository
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(private val remoteDogDataSource: RemoteDogDatasource) : DogRepository {

    override suspend fun getDogList(): Result<List<Dog>> = remoteDogDataSource.getDogList()
}
