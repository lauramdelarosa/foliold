package com.delarosa.folio.home.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.delarosa.folio.databinding.FragmentHomeBinding
import com.delarosa.folio.home.domain.entities.Dog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), DogOptions {

    private val viewModel: HomeViewModel by viewModels()
    private var dogAdapter: DogAdapter? = null
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleEvents()
        initViews()
        viewModel.onViewCreated()
    }

    private fun initViews() {
        binding.rvDogList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        dogAdapter = DogAdapter(this)
        binding.rvDogList.adapter = dogAdapter
    }

    private fun handleEvents() {
        lifecycleScope.launch {
            viewModel.events.collect {
                when (it) {
                    is HomeViewModel.Event.ShowDogList -> dogAdapter?.submitList(it.list)
                    is HomeViewModel.Event.GoToDogDetail -> {
                    }
                }
            }
        }
    }

    override fun onOptionClicked(dogClicked: Dog) {
        TODO("Not yet implemented")
    }
}
