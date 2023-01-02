package com.delarosa.folio.home.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.delarosa.folio.databinding.ItemDogBinding
import com.delarosa.folio.home.domain.entities.Dog

class DogAdapter(private val listener: DogOptions) : ListAdapter<Dog, DogViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val binding = ItemDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogViewHolder(binding, listener)
    }

    override fun onBindViewHolder(viewHolder: DogViewHolder, position: Int) = viewHolder.bindView(getItem(position))

    companion object {
        val diffUtilCallback = object : DiffUtil.ItemCallback<Dog>() {
            override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean = oldItem.isEquals(newItem)
        }
    }
}
