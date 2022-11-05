package com.example.bitok.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.bitok.data.database.AppDatabase
import com.example.bitok.data.mapper.CoinMapper
import com.example.bitok.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {
    private val coinInfoDao = AppDatabase.getInstance(context).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService

    private val mapper = CoinMapper()

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNameListToString(topCoins)
                val jsonCointainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonCointainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {

            }
            delay(10000)
        }
    }

    companion object{
        const val NAME = "refrashDataWorker"

        fun makerRequest(): OneTimeWorkRequest{
            return  OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}