package com.malykhnik.bulletinboard_kotlin.dto

import com.malykhnik.bulletinboard_kotlin.dto.message_dto.MessageDto

data class TopicDto(
    val id: Long,
    val title: String,
    val messages: MutableList<MessageDto> = mutableListOf()
)

