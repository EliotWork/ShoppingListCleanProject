package com.example.di.example2.data.repositoryimpl

import com.example.di.example2.data.datasourceinterface.ExampleLocalDataSource
import com.example.di.example2.data.datasourceinterface.ExampleRemoteDataSource
import com.example.di.example2.domain.repository.ExampleRepository

class ExampleRepositoryImpl(
    private val localDataSource: ExampleLocalDataSource,
    private val remoteDataSource: ExampleRemoteDataSource
) : ExampleRepository {

    override fun method() {

    }
}
