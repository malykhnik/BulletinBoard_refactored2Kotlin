package com.malykhnik.bulletinboard_kotlin.dto

import com.malykhnik.bulletinboard_kotlin.entity.Message
import com.malykhnik.bulletinboard_kotlin.entity.Topic

data class TopicDto(
    val id: Long,
    val title: String,
    val messages: MutableList<MessageDto> = mutableListOf()
)

