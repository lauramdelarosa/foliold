package com.delarosa.folio.home.domain.usecases

import com.delarosa.folio.home.domain.entities.Dog
import com.delarosa.folio.home.domain.repositories.DogRepository
import javax.inject.Inject

class GetDogListInteractor @Inject constructor(private val dogRepository: DogRepository) {

    suspend operator fun invoke(): Result<List<Dog>> = dogRepository.getDogList()
}
