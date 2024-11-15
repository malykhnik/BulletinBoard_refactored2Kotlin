package com.malykhnik.bulletinboard_kotlin.rest

import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_in_dto.SignInRequestDto
import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_in_dto.JwtResponseDto
import com.malykhnik.bulletinboard_kotlin.service.security_logic.AuthenticationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationService: AuthenticationService
) {
    @PostMapping
    fun authenticate(@RequestBody signInRequestDto: SignInRequestDto):
            JwtResponseDto = authenticationService.authentication(signInRequestDto)

}