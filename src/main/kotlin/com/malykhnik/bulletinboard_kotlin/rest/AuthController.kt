package com.malykhnik.bulletinboard_kotlin.rest

import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.UserRequestDto
import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.UserResponseDto
import com.malykhnik.bulletinboard_kotlin.entity.User
import com.malykhnik.bulletinboard_kotlin.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class AuthController(
    private val userService: UserService
) {
    @PostMapping("/register")
    fun createUser(@RequestBody userRequestDto: UserRequestDto): ResponseEntity<UserResponseDto> {
        val userEntity = userRequestDto.toEntity()
        return ResponseEntity.ok(userService.createUser(userEntity).toDto())
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserResponseDto>> {
        return ResponseEntity.ok(userService.getAllUsers().toDtoList())
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable(name = "id") id: Long): ResponseEntity<UserResponseDto> {
        return ResponseEntity.ok(userService.findById(id).toDto())
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable(name = "id") id: Long): ResponseEntity<Unit> {
        return ResponseEntity.ok(userService.deleteByID(id))
    }

}

fun UserRequestDto.toEntity() = User(
    id = id,
    email = email,
    password = password,
    role = role
)

fun User.toDto() = UserResponseDto(
    id = id,
    email = email,
    role = role
)

fun List<User>.toDtoList(): List<UserResponseDto> = this.map { it.toDto() }