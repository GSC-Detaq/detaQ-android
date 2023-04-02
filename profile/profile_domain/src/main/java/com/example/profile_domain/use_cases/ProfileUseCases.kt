package com.example.profile_domain.use_cases

data class ProfileUseCases(
    val getUserPersonal: GetUserPersonal,
    val addNewFamily: AddNewFamily,
    val getFamily: GetFamily,
    val getPatient: GetPatient,
    val connectWristband: ConnectWristband
)
