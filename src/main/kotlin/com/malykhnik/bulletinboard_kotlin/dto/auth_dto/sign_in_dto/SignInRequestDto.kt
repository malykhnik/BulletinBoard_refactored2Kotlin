package com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_in_dto

data class SignInRequestDto(
    val email: String,
    val password: String,
)