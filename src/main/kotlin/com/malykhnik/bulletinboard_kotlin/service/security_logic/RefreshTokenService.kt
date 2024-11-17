package com.malykhnik.bulletinboard_kotlin.service.security_logic

import com.malykhnik.bulletinboard_kotlin.configuration.properties.JwtProperties
import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.RefreshTokenDto
import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_in_dto.AuthResponseDto
import com.malykhnik.bulletinboard_kotlin.entity.RefreshToken
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.token_exception.RefreshTokenNotFoundException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.token_exception.TokenExpiredException
import com.malykhnik.bulletinboard_kotlin.repository.RefreshTokenRepository
import com.malykhnik.bulletinboard_kotlin.repository.UserRepository
import com.malykhnik.bulletinboard_kotlin.util.WorkWithAuth
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class RefreshTokenService(
    private val refreshRepo: RefreshTokenRepository,
    private val userRepo: UserRepository,
    private val userDetailsService: MyUserDetailsService,
    private val jwtService: JwtService,
    private val jwtProperties: JwtProperties
) {
    fun createOrUpdateRefreshToken(email: String): String {
        val user = userRepo.findByEmail(email) ?: throw UsernameNotFoundException("Такого юзера нет!")
        val refreshToken = refreshRepo.findByUser(user)
        val savedRefreshToken: RefreshToken
        if (refreshToken != null) {
            savedRefreshToken = RefreshToken(
                id = refreshToken.id,
                user = refreshToken.user,
                token = UUID.randomUUID().toString(),
                expiryDate = Instant.now().plusMillis(jwtProperties.refreshTokenExpiration)
            )
            refreshRepo.save(savedRefreshToken)
        } else {
            savedRefreshToken = RefreshToken(
                user = user,
                token = UUID.randomUUID().toString(),
                expiryDate = Instant.now().plusMillis(jwtProperties.refreshTokenExpiration)
            )
            refreshRepo.save(savedRefreshToken)
        }
        return savedRefreshToken.token
    }

    fun refreshJwt(refreshTokenDto: RefreshTokenDto): AuthResponseDto {
        val token = findByToken(refreshTokenDto.refreshToken)
        val verifiedToken = verifyExpiration(token)
        return AuthResponseDto(
            accessToken = jwtService.generate(
                email = token.user.email,
                expirationDate = getAccessTokenExpiration()
            ),
            refreshToken = verifiedToken.token
        )
    }

    fun verifyExpiration(token: RefreshToken): RefreshToken {
        if (token.expiryDate.isBefore(Instant.now())) {
            refreshRepo.delete(token)
            throw TokenExpiredException("Refresh token ${token.token} is expired. Please make a new login..!")
        }
        return token
    }

    fun findByToken(token: String): RefreshToken =
        refreshRepo.findByToken(token)
            ?: throw RefreshTokenNotFoundException("Refresh token with token $token not found")

    fun getAccessTokenExpiration(): Date =
        Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
}