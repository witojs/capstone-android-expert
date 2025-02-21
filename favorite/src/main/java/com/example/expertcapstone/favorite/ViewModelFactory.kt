package com.example.expertcapstone.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expertcapstone.core.domain.useCase.GameUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val gameUseCase: GameUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(gameUseCase) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}