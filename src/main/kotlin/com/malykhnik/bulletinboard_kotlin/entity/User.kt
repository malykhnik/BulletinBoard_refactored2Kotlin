package com.malykhnik.bulletinboard_kotlin.entity

import com.malykhnik.bulletinboard_kotlin.dto.TopicDto
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int
) {

}