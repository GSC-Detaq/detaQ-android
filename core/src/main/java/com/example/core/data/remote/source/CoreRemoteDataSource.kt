package com.example.core.data.remote.source

import com.example.core.data.remote.dto.request.InsertContactRequest
import com.example.core.data.remote.dto.request.UpdateFcmTokenRequest
import com.example.core.data.remote.dto.response.ContactData
import com.example.core.data.remote.service.contact.ContactApiService
import com.example.core.data.remote.service.fcm.FcmApiService
import com.example.core.data.remote.utils.tryCatch
import com.example.core.data.utils.ApiResponse
import com.example.core.domain.dispatchers.DispatchersProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoreRemoteDataSource @Inject constructor(
    private val contactApiService: ContactApiService,
    private val fcmApiService: FcmApiService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun insertContact(
        request: InsertContactRequest
    ): ApiResponse<Unit> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = contactApiService.insertContact(request)

                if (result.meta.success) {
                    ApiResponse.Success(Unit)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun getContactById(
        id: String
    ): ApiResponse<ContactData> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = contactApiService.getContactById(
                    id = id
                )

                if (result.meta.success) {
                    ApiResponse.Success(result.data)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun getContacts(): ApiResponse<List<ContactData>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = contactApiService.getAllContacts()

                if (result.meta.success) {
                    ApiResponse.Success(result.data)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun updateFcmToken(
        request: UpdateFcmTokenRequest
    ): ApiResponse<String> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = fcmApiService.updateToken(request)

                if (result.meta.success) {
                    ApiResponse.Success(result.meta.message)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }
}