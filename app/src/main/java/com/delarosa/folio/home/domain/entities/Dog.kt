package com.delarosa.folio.home.domain.entities

data class Dog(
    val id: Int,
    val name: String,
    val image: String,
    val origin: String,
    val temperament: String,
    val lifeSpan: String
) {
    fun isEquals(other: Dog): Boolean {
        return (
            this.id == other.id &&
                this.name == other.name &&
                this.image == other.image &&
                this.temperament == other.temperament &&
                this.lifeSpan == other.lifeSpan &&
                this.origin == other.origin
            )
    }
}
