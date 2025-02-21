package com.example.expertcapstone.presenter.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expertcapstone.core.data.Resource
import com.example.expertcapstone.core.domain.model.Game
import com.example.expertcapstone.core.domain.useCase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val gameUseCase: GameUseCase) : ViewModel() {
    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>> = _games

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    init {
        getGames()
    }

    private fun getGames() = viewModelScope.launch {
        gameUseCase.getGames().collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _isLoading.value = true
                    _isError.value = false
                }

                is Resource.Success -> {
                    _isLoading.value = false
                    _isError.value = false
                    _games.value = result.data!!
                }

                is Resource.Error -> {
                    _isLoading.value = false
                    _isError.value = true
                }
            }
        }
    }
}