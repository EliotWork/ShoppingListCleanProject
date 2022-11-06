package com.example.di.example2.data.repositoryimpl

import com.example.di.example2.data.db.ExampleDatabase
import com.example.di.example2.data.datasourceinterface.ExampleLocalDataSource

class ExampleLocalDataSourceImpl(
    private val database: ExampleDatabase
) : ExampleLocalDataSource {

    override fun method() {

    }
}
