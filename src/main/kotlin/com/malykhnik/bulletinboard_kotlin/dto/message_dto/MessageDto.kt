package com.malykhnik.bulletinboard_kotlin.dto.message_dto

import java.util.*

data class MessageDto(
    val id: Long,
    val author: String? = null,
    val message: String,
    val date: Date? = null
)