package com.example

import com.example.repo.DatabaseFactory
import com.example.repo.studentrepository
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.locations.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database

fun Application.configureSerialization() {


    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
    routing {



    }
}

