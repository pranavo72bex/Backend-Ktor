package com.example.plugins

import com.example.route.authRouting
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
       authRouting()
    }
}
