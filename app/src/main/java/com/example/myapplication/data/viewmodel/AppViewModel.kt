package com.example.myapplication.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.SettingsRepository
import kotlinx.coroutines.launch

class AppViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    var isFirstLaunch by mutableStateOf<Boolean?>(null)
        private set

    init {
        viewModelScope.launch {
            isFirstLaunch = settingsRepository.isFirstLaunch()
        }
    }

    fun setFirstLaunchDone() {
        viewModelScope.launch {
            settingsRepository.setFirstLaunchDone()
            isFirstLaunch = false
        }
    }
}
