package com.example.bitok.domain.usecase.load

import com.example.bitok.domain.repository.CoinRepository

class LoadDataUseCase(
    private val repository: CoinRepository
) {

   suspend operator fun invoke() = repository.loadData()
}