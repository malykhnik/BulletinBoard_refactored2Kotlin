package com.malykhnik.bulletinboard_kotlin.rest

import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_up_dto.SignUpRequestDto
import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_up_dto.SignUpResponseDto
import com.malykhnik.bulletinboard_kotlin.entity.User
import com.malykhnik.bulletinboard_kotlin.service.business_logic.UserService
import com.malykhnik.bulletinboard_kotlin.util.toDto
import com.malykhnik.bulletinboard_kotlin.util.toEntity
import com.malykhnik.bulletinboard_kotlin.util.toUserDtoList
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/save")
    fun createUser(@RequestBody signUpRequestDto: SignUpRequestDto): ResponseEntity<SignUpResponseDto> {
        return ResponseEntity.ok(userService.createUser(signUpRequestDto.toEntity()).toDto())
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<SignUpResponseDto>> {
        return ResponseEntity.ok(userService.getAllUsers().toUserDtoList())
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable(name = "id") id: Long): ResponseEntity<SignUpResponseDto> {
        return ResponseEntity.ok(userService.findById(id).toDto())
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable(name = "id") id: Long): ResponseEntity<Unit> {
        return ResponseEntity.ok(userService.deleteByID(id))
    }

}

