package com.delarosa.folio.core.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

abstract class BaseViewModel<State, Event>(val viewState: State) : ViewModel() {

    private val mutableState = MutableSharedFlow<State>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    val states: Flow<State> get() = mutableState

    protected val mutableEvent = MutableSharedFlow<Event>(extraBufferCapacity = 1)

    val events: Flow<Event> get() = mutableEvent

    private val mutex: Mutex = Mutex()

    protected suspend fun updateState(state: (State) -> State) {
        mutex.withLock {
            mutableState.emit(state(currentState()))
        }
    }

    protected fun currentState() = mutableState.replayCache.firstOrNull() ?: viewState
}
