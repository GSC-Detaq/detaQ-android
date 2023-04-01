package com.example.home_data.remote.source

import com.example.core.domain.dispatchers.DispatchersProvider
import com.example.home_data.remote.service.HomeApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRemoteDataSource @Inject constructor(
    private val apiService: HomeApiService,
    private val dispatchers: DispatchersProvider
) {
}