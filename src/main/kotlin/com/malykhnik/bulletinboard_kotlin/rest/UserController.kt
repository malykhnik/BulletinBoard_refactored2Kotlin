package com.malykhnik.bulletinboard_kotlin.rest

import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_up_dto.SignUpRequestDto
import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_up_dto.SignUpResponseDto
import com.malykhnik.bulletinboard_kotlin.entity.User
import com.malykhnik.bulletinboard_kotlin.service.business_logic.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/save")
    fun createUser(@RequestBody signUpRequestDto: SignUpRequestDto): ResponseEntity<SignUpResponseDto> {
        val userEntity = signUpRequestDto.toEntity()
        return ResponseEntity.ok(userService.createUser(userEntity).toDto())
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<SignUpResponseDto>> {
        return ResponseEntity.ok(userService.getAllUsers().toDtoList())
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

fun List<User>.toDtoList(): List<SignUpResponseDto> = this.map { it.toDto() }