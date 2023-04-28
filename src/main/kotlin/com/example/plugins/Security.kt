package com.example.plugins

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*

fun Application.configureSecurity() {

    val jwtAudience = System.getenv("jwt.audience")
    val jwtIssuer = System.getenv("jwt.domain")
    val jwtSecrete = System.getenv("jwt.secret")

    val  CLAIM = "email"
    
    authentication {
            jwt {
                verifier(
                    JWT
                        .require(Algorithm.HMAC256(jwtSecrete))
                        .withAudience(jwtAudience)
                        .withIssuer(jwtIssuer)
                        .build()
                )
                validate { credential ->
                    if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
                }
            }
        }
}
