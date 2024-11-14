package com.malykhnik.bulletinboard_kotlin.entity

import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.util.Date

@Entity
@Table(name = "messages")
class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val author: String,
    val message: String,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val date: Date
)
