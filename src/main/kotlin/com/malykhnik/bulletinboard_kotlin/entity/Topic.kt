package com.malykhnik.bulletinboard_kotlin.entity

import com.malykhnik.bulletinboard_kotlin.dto.TopicDto
import jakarta.persistence.*

@Entity
@Table(name = "topics")
class Topic(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val title: String,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id") // Указываем внешний ключ в таблице messages
    val messages: MutableList<Message> = mutableListOf()
) {

}