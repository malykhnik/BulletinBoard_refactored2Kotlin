package com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_up_dto

import com.malykhnik.bulletinboard_kotlin.entity.Role

data class SignUpResponseDto(
    val id: Long,
    val email: String,
    val role: Role
)