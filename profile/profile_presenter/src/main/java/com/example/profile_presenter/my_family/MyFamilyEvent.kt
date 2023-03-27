package com.example.profile_presenter.my_family

sealed class MyFamilyEvent {
    data class Edit(val isEdit: Boolean): MyFamilyEvent()
    data class OnDelete(val username: String): MyFamilyEvent()
}