package com.delarosa.folio.home.domain.repositories

import com.delarosa.folio.home.domain.entities.Dog

interface DogRepository {

    suspend fun getDogList(): Result<List<Dog>>
}
