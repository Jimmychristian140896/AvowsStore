package com.jimmy.avowsstore.presentation.summary

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.jimmy.avowsstore.core.data.asUiText
import com.jimmy.avowsstore.core.data.onFailure
import com.jimmy.avowsstore.core.data.onSuccess
import com.jimmy.avowsstore.domain.repository.TransactionRepository
import com.jimmy.avowsstore.navigation.Route
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SummaryViewModel(
    private val transactionRepository: TransactionRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val route: Route.Summary = savedStateHandle.toRoute()
    val id: String = route.id

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(SummaryState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
                getTransaction(id)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = SummaryState()
        )

    private val _eventChannel = Channel<SummaryEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun onAction(action: SummaryAction) {
        when (action) {
            SummaryAction.Close -> {
                viewModelScope.launch {
                    _eventChannel.send(SummaryEvent.Close)
                }
            }

            SummaryAction.OnTryAgain -> {
                _state.update { it.copy(
                    error = null,
                ) }
                getTransaction(id)
            }
            else -> TODO("Handle actions")
        }
    }

    private fun getTransaction(id: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            transactionRepository.getTransactionById(id)
                .onSuccess { transaction ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            transaction = transaction
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.asUiText()
                        )
                    }
                }
        }
    }

}