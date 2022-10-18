package com.example.coroutinestart.domain.usecases

import com.example.coroutinestart.domain.entity.GameSettings
import com.example.coroutinestart.domain.entity.Level
import com.example.coroutinestart.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level):GameSettings{
        return  repository.getGameSettings(level)
    }

}