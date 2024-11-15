package com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_up_dto

import com.malykhnik.bulletinboard_kotlin.entity.Role

data class SignUpRequestDto(
    val id: Long,
    val email: String,
    val password: String,
    val role: Role
)