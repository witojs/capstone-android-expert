package com.example.expertcapstone.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.expertcapstone.core.domain.useCase.GameUseCase

class FavoriteViewModel(gameUseCase: GameUseCase) : ViewModel() {
    val favoriteGame = gameUseCase.getAllGames().asLiveData()
}