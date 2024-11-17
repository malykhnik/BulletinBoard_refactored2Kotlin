package com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_in_dto

data class AuthResponseDto(
    val accessToken: String,
    val refreshToken: String
)