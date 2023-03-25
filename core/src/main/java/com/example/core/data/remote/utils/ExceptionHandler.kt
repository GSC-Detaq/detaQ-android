package com.example.core.data.remote.utils

import com.example.core.data.utils.ApiResponse
import io.ktor.client.plugins.*
import timber.log.Timber

suspend fun <T> tryCatch(
    httpCall: suspend () -> ApiResponse<T>
): ApiResponse<T> =
    try {
        httpCall()
    } catch (e: RedirectResponseException) {
        Timber.e("Error: ${e.response.status.description}")
        ApiResponse.Error("Error: ${e.response.status.description}")
    } catch (e: ClientRequestException) {
        Timber.e("Error: ${e.response.status.description}")
        ApiResponse.Error("Error: ${e.response.status.description}")
    } catch (e: ServerResponseException) {
        Timber.e("Error: ${e.response.status.description}")
        ApiResponse.Error("Error: ${e.response.status.description}")
    } catch (e: Exception) {
        Timber.e("Error: ${e.message}")
        ApiResponse.Error("Error: ${e.message}")
    }