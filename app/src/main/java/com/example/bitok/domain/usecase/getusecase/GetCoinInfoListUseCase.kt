package com.example.bitok.domain.usecase.getusecase

import com.example.bitok.domain.repository.CoinRepository

class GetCoinInfoListUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke() = repository.getCoinInfoList()
}