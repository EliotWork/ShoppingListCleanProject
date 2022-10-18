package com.example.coroutinestart.domain.repository

import com.example.coroutinestart.domain.entity.GameSettings
import com.example.coroutinestart.domain.entity.Level
import com.example.coroutinestart.domain.entity.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue:Int,
        countOfOptions:Int
    ): Question

    fun getGameSettings(level: Level):GameSettings
}