package com.malykhnik.bulletinboard_kotlin.util

import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_up_dto.SignUpRequestDto
import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_up_dto.SignUpResponseDto
import com.malykhnik.bulletinboard_kotlin.dto.message_dto.MessageDto
import com.malykhnik.bulletinboard_kotlin.dto.topic_dto.TopicDto
import com.malykhnik.bulletinboard_kotlin.entity.Message
import com.malykhnik.bulletinboard_kotlin.entity.Topic
import com.malykhnik.bulletinboard_kotlin.entity.User

fun SignUpRequestDto.toEntity() = User(
    id = id,
    email = email,
    password = password,
    role = role
)

fun User.toDto() = SignUpResponseDto(
    id = id,
    email = email,
    role = role
)


fun MessageDto.toEntity(topic: Topic): Message {
    return Message(
        id = this.id,
        author = WorkWithAuth.getUsernameByAuthUser(),
        message = this.message,
        date = this.date!!,
        topic = topic
    )
}

fun Message.toDto(): MessageDto {
    return MessageDto(
        id = this.id,
        author = this.author,
        message = this.message,
        date = this.date
    )
}


fun TopicDto.toEntity(): Topic {
    val topic = Topic(
        id = this.id,
        title = this.title
    )
    topic.messages.addAll(
        this.messages.map { messageDto ->
            Message(
                id = messageDto.id,
                author = WorkWithAuth.getUsernameByAuthUser(),
                message = messageDto.message,
                date = messageDto.date!!,
                topic = topic
            )
        }
    )
    return topic
}

fun Topic.toDto(): TopicDto {
    return TopicDto(
        id = this.id,
        title = this.title,
        messages = this.messages.map { message ->
            MessageDto(
                id = message.id,
                author = message.author,
                message = message.message,
                date = message.date
            )
        }.toMutableList()
    )
}

fun List<User>.toUserDtoList(): List<SignUpResponseDto> = this.map { it.toDto() }

fun List<Message>.toMessageDtoList(): List<MessageDto> = this.map { it.toDto() }

fun List<Topic>.toTopicDtoList(): List<TopicDto> = this.map { it.toDto() }

