package com.example.bitok.domain.repository

import androidx.lifecycle.LiveData
import com.example.bitok.domain.entity.CoinInfo

interface CoinRepository {
    fun getCoinInfoList(): LiveData<List<CoinInfo>>


    fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo>
}