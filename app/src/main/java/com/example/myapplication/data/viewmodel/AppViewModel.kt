package com.example.myapplication.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _isFirstLaunch = MutableStateFlow<Boolean?>(null)
    val isFirstLaunch: StateFlow<Boolean?> = _isFirstLaunch.asStateFlow()

    init {
        viewModelScope.launch {
            _isFirstLaunch.value = settingsRepository.isFirstLaunch()
        }
    }

    fun setFirstLaunchDone() {
        viewModelScope.launch {
            settingsRepository.setFirstLaunchDone()
            _isFirstLaunch.value = false
        }
    }
}
