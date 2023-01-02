package com.delarosa.folio.home.presentation.server

import com.delarosa.folio.home.domain.entities.Dog
import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("origin") val origin: String?,
    @SerializedName("image") val image: Image,
    @SerializedName("temperament") val temperament: String?,
    @SerializedName("life_span") val lifeSpan: String
) {
    fun toDog(): Dog {
        return Dog(
            id = id, name = name, image = image.url, temperament = temperament.orEmpty(), lifeSpan = lifeSpan,
            origin =
            origin.orEmpty()
        )
    }
}

data class Image(
    @SerializedName("url") val url: String
)
