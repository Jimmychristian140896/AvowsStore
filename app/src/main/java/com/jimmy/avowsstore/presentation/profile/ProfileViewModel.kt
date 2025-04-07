package com.jimmy.avowsstore.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jimmy.avowsstore.core.data.asUiText
import com.jimmy.avowsstore.core.data.onFailure
import com.jimmy.avowsstore.core.data.onSuccess
import com.jimmy.avowsstore.data.local.SharedPreferenceManager
import com.jimmy.avowsstore.domain.model.User
import com.jimmy.avowsstore.domain.repository.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: UserRepository,
    private val sharedPreferenceManager: SharedPreferenceManager
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(ProfileState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
                getProfile()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = ProfileState()
        )

    private val _eventChannel = Channel<ProfileEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun onAction(action: ProfileAction) {
        when (action) {
            ProfileAction.Logout -> {
                viewModelScope.launch {
                    sharedPreferenceManager.logout()
                    _eventChannel.send(ProfileEvent.Logout)
                }
            }

            ProfileAction.OnTryAgain -> {
                _state.update { it.copy(
                    error = null,
                ) }
                getProfile()
            }
            else -> TODO("Handle actions")
        }
    }

    private fun getProfile() {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }
            userRepository.getUserById(sharedPreferenceManager.getUserId())
                .onSuccess { user ->
                    _state.update { it.copy(
                        isLoading = false,
                        user = user
                    ) }
                }
                .onFailure { error ->
                    _state.update { it.copy(
                        isLoading = false,
                        error = error.asUiText()
                    ) }
                }
        }
    }

}