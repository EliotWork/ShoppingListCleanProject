package com.example.di.example2.domain.usecase

import com.example.di.example2.domain.repository.ExampleRepository

class ExampleUseCase(
    private val repository: ExampleRepository
) {

    operator fun invoke() {

    }
}
