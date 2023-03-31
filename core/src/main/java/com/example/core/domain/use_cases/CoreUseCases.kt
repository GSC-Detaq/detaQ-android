package com.example.core.domain.use_cases

data class CoreUseCases(
    val insertContact: InsertContact,
    val getContactById: GetContactById,
    val getContacts: GetContacts,
    val validateEmail: ValidateEmail,
    val updateFcmToken: UpdateFcmToken
)
