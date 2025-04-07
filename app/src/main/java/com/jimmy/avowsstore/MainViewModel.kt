package com.jimmy.avowsstore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jimmy.avowsstore.data.local.SharedPreferenceManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val sharedPreferenceManager: SharedPreferenceManager
): ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        initLoading()
    }

    private fun initLoading() {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true,
                isLoggedIn = sharedPreferenceManager.isLogin()
            ) }
            delay(1000L)

            _state.update { it.copy(
                isLoading = false,
            ) }
        }
    }
}