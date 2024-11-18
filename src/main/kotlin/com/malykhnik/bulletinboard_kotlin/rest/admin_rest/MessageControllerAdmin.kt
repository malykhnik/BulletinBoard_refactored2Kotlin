package com.malykhnik.bulletinboard_kotlin.rest.admin_rest

import com.malykhnik.bulletinboard_kotlin.dto.message_dto.MessageDto
import com.malykhnik.bulletinboard_kotlin.dto.message_dto.MessageDtoForUpdate
import com.malykhnik.bulletinboard_kotlin.service.business_logic.MessageService
import com.malykhnik.bulletinboard_kotlin.util.toDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/messages")
class MessageControllerAdmin(
    private val messageService: MessageService
) {
    @PatchMapping("/{messageId}")
    fun updateMessageByMessageIdAndAuthor(@PathVariable(name = "messageId") messageId: Long,
                                          @RequestBody messageDtoForUpdate: MessageDtoForUpdate
    ): ResponseEntity<MessageDto> {
        return ResponseEntity.ok(messageService.updateMessage(messageId, messageDtoForUpdate).toDto())
    }

    @DeleteMapping("/{messageId}")
    fun deleteMessageByMessageIdAndAuthor(@PathVariable(name = "messageId") messageId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(messageService.deleteMessage(messageId))
    }
}