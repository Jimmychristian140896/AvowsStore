package com.jimmy.avowsstore.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jimmy.avowsstore.core.data.asUiText
import com.jimmy.avowsstore.core.data.onFailure
import com.jimmy.avowsstore.core.data.onSuccess
import com.jimmy.avowsstore.data.local.SharedPreferenceManager
import com.jimmy.avowsstore.domain.model.User
import com.jimmy.avowsstore.domain.repository.AuthRepository
import com.jimmy.avowsstore.domain.repository.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val sharedPreferenceManager: SharedPreferenceManager
): ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _eventChannel = Channel<LoginEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun onAction(action: LoginAction) {
        when(action) {
            is LoginAction.OnLoginClicked -> {
                login()
            }

            is LoginAction.OnUsernameChanged -> {
                viewModelScope.launch {
                    _state.update { it.copy(
                        username = action.username
                    )
                }}
            }
            is LoginAction.OnPasswordChanged -> {
                viewModelScope.launch {
                    _state.update { it.copy(
                        password = action.password
                    ) }
                }
            }

            LoginAction.OnPasswordVisibleChange -> {
                viewModelScope.launch {
                    _state.update { it.copy(
                        passwordVisible = !it.passwordVisible
                    ) }
                }
            }
            else -> {

            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }

            authRepository.login(
                username = _state.value.username,
                password = _state.value.password
            ).onSuccess {

                _state.update { it.copy(
                    isLoading = false
                ) }
                sharedPreferenceManager.login(it)
                _eventChannel.trySend(LoginEvent.OnLoginSuccess)
            }.onFailure {
                _state.update { it.copy(
                    isLoading = false
                ) }
                _eventChannel.trySend(LoginEvent.OnLoginFailed(it.asUiText()))
            }

        }
    }
}