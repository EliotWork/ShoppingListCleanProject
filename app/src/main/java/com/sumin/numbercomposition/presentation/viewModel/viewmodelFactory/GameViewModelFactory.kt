package com.sumin.numbercomposition.presentation.viewModel.viewmodelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.sumin.numbercomposition.domain.entity.Level
import com.sumin.numbercomposition.presentation.viewModel.GameViewModel

class GameViewModelFactory(
    private val level: Level,
    private val application: Application
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GameViewModel::class.java)){
            return GameViewModel(application,level) as T
        }else{
            throw RuntimeException("Unknown view model class $modelClass")
        }

    }
}