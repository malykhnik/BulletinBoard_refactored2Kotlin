package com.malykhnik.bulletinboard_kotlin.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.malykhnik.bulletinboard_kotlin.entity.Topic
import java.util.*

data class MessageDto(
    val id: Long,
    val author: String,
    val message: String,
    val date: Date
)