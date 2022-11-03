package com.example.bitok.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bitok.data.network.ApiFactory.BASE_IMAGE_URL
import com.example.bitok.utils.convertTimestampToTime
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinInfoDbModel(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String?,
    val price: String?,
    val lastUpdate: Long?,
    val highDay: String?,
    val lowDay: String?,
    val lastMarket: String?,
    val imageUrl: String?
)