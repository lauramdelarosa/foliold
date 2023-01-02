package com.delarosa.folio.home.presentation.view

import com.delarosa.folio.home.domain.entities.Dog

interface DogOptions {

    fun onOptionClicked(dogClicked: Dog)
}
