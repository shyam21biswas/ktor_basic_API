@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.example

import com.example.repo.DatabaseFactory
import com.example.repo.studentrepository
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)

}

fun Application.module() {

    DatabaseFactory.init()
    val db = studentrepository()
    install(Locations)
    {

    }

    install (ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    routing {
        student(db)

        get("/") {
            call.respondText("luv u ved❤️!")
        }
    }




}

const val  API_V1 = "v1/"