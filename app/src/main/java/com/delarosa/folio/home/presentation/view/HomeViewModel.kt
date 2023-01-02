package com.delarosa.folio.home.presentation.view

import androidx.lifecycle.viewModelScope
import com.delarosa.folio.core.presentation.BaseViewModel
import com.delarosa.folio.home.domain.entities.Dog
import com.delarosa.folio.home.domain.usecases.GetDogListInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDogListInteractor: GetDogListInteractor,
    initialState: State
) : BaseViewModel<HomeViewModel.State, HomeViewModel.Event>(initialState) {

    fun onViewCreated() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { getDogListInteractor() }
                .onSuccess { list ->
                    updateState {
                        it.copy(dogList = list)
                    }
                    mutableEvent.emit(Event.ShowDogList(list))
                }.onFailure {
                    print(it)
                }
        }
    }

    fun onDogItemClicked() {
        viewModelScope.launch {
            mutableEvent.emit(Event.GoToDogDetail)
        }
    }

    data class State constructor(
        val dogList: List<Dog>
    ) {
        @Inject
        constructor() : this(
            dogList = emptyList(),
        )
    }

    sealed class Event {
        data class ShowDogList(val list: List<Dog>) : Event()
        object GoToDogDetail : Event()
    }
}
