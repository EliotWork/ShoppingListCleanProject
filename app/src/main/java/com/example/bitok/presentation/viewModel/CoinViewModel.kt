package com.example.bitok.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bitok.data.network.model.CoinInfoDto
import com.example.bitok.data.repository.CoinRepositoryImpl
import com.example.bitok.domain.usecase.getusecase.GetCoinInfoListUseCase
import com.example.bitok.domain.usecase.getusecase.GetCoinInfoUseCase
import com.example.bitok.domain.usecase.load.LoadDataUseCase
import kotlinx.coroutines.launch


class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)


    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)


    init {
        loadDataUseCase()
    }

}