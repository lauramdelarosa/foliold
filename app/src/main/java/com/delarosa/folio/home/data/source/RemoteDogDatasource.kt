package com.delarosa.folio.home.data.source

import com.delarosa.folio.home.domain.entities.Dog

interface RemoteDogDatasource {

    suspend fun getDogList(): Result<List<Dog>>
}
