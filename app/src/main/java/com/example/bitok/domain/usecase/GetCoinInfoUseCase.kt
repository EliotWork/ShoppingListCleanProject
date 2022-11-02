package com.example.bitok.domain.usecase

import com.example.bitok.domain.repository.CoinRepository

class GetCoinInfoUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke(fromSymbol:String) = repository.getCoinInfo(fromSymbol)
}