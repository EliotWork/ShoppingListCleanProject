package com.example.di.example2.data.repositoryimpl

import com.example.di.example2.data.api.ExampleApiService
import com.example.di.example2.data.datasourceinterface.ExampleRemoteDataSource

class ExampleRemoteDataSourceImpl(
    private val apiService: ExampleApiService
) : ExampleRemoteDataSource {

    override fun method() {

    }
}
