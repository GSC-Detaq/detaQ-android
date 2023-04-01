package com.example.home_data.repository

import com.example.home_data.remote.source.HomeRemoteDataSource
import com.example.home_domain.repository.HomeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource
): HomeRepository {
}