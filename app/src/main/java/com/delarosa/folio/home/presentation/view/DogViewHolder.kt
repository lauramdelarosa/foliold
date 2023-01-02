package com.delarosa.folio.home.presentation.view

import androidx.recyclerview.widget.RecyclerView
import com.delarosa.folio.core.presentation.glide.loadImageFromPathWithBaseUrl
import com.delarosa.folio.databinding.ItemDogBinding
import com.delarosa.folio.home.domain.entities.Dog

class DogViewHolder(private val binding: ItemDogBinding, private val listener: DogOptions) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindView(dog: Dog) {
        with(binding) {
            tvDogName.text = dog.name

            ivDog.loadImageFromPathWithBaseUrl(dog.image)

            clDogItem.setOnClickListener {
                listener.onOptionClicked(dog)
            }
        }
    }
}
