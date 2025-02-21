package com.example.expertcapstone.presenter.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expertcapstone.core.data.Resource
import com.example.expertcapstone.core.domain.model.GameDetail
import com.example.expertcapstone.core.domain.useCase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val gameUseCase: GameUseCase) : ViewModel() {
    private val _gameDetail = MutableLiveData<GameDetail>()
    val gameDetail: LiveData<GameDetail> = _gameDetail

    private val _favoriteGame = MutableLiveData<GameDetail>()
    val favoriteGame: LiveData<GameDetail> = _favoriteGame

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    fun getDetailGame(id: Int) = viewModelScope.launch {
        gameUseCase.getDetailGame(id).collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _isLoading.value = true
                    _isError.value = false
                }

                is Resource.Success -> {
                    _isLoading.value = false
                    _isError.value = false
                    _gameDetail.value = result.data!!
                }

                is Resource.Error -> {
                    _isLoading.value = false
                    _isError.value = true
                }
            }
        }
    }

    fun getFavoriteGameById(id: Int) {
        viewModelScope.launch {
            gameUseCase.getFavoriteGameById(id).collect { result ->
                _favoriteGame.value = result
            }
        }
    }

    fun insertGame(game: GameDetail) = viewModelScope.launch {
        gameUseCase.insertGame(game)
    }

    fun deleteGame(game: GameDetail) = viewModelScope.launch {
        gameUseCase.deleteGame(game)
    }

}