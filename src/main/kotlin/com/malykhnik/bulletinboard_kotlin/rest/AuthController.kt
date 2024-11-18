package com.malykhnik.bulletinboard_kotlin.rest

import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.RefreshTokenDto
import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_in_dto.SignInRequestDto
import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_in_dto.AuthResponseDto
import com.malykhnik.bulletinboard_kotlin.service.security_logic.AuthenticationService
import com.malykhnik.bulletinboard_kotlin.service.security_logic.RefreshTokenService
import com.malykhnik.bulletinboard_kotlin.service.security_logic.TokenBlackList
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationService: AuthenticationService,
    private val refreshTokenService: RefreshTokenService,
    private val tokenBlackList: TokenBlackList
) {
    @PostMapping
    fun authenticate(@RequestBody signInRequestDto: SignInRequestDto):
            AuthResponseDto = authenticationService.authentication(signInRequestDto)

    @PostMapping("/refreshJwt")
    fun refreshJwt(@RequestBody refreshTokenDto: RefreshTokenDto): ResponseEntity<AuthResponseDto> {
        return ResponseEntity.ok(refreshTokenService.refreshJwt(refreshTokenDto))
    }

    @PostMapping("/logout")
    fun logout(request: HttpServletRequest): ResponseEntity<String> {
        tokenBlackList.addToBlackList(extractToken(request.getHeader("Authorization")))
        return ResponseEntity.ok("Logged out successfully")
    }

    private fun extractToken(header: String): String  {
        return header.extractTokenValue()
    }

    private fun String.extractTokenValue() =
        this.substringAfter("Bearer ")

}
