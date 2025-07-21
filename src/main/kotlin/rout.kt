package com.example

import com.example.StudentTable.userId
import com.example.repo.studentrepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.routing.post
import org.jetbrains.exposed.sql.Database

const val CREATE_STUDENT = "st"
@OptIn(KtorExperimentalLocationsAPI::class)
@Location(CREATE_STUDENT)
class CreateStudent


fun Route.student (
        db : studentrepository
    ) {

        post(CREATE_STUDENT) {
            val parameter = call.receive<Parameters>()
            // name id from table define
            val name = parameter["name"] ?: return@post call.respondText(
                "Missing or malformed name",
                status = HttpStatusCode.OK
            )
            val age = parameter["age"]?.toInt() ?: return@post call.respondText(
                "Missing or malformed age",
                status = HttpStatusCode.OK
            )
            try{

                val student  = db.insert(name,age.toLong())
                student?.userId?.let {
                    call.respondText(student.toString(), status = HttpStatusCode.OK)
                }



            } catch (e: Throwable) {
                call.respondText("${e.message}", status = HttpStatusCode.OK)
            }

        }

    get("st") {
        try{

            val student  = db.getAllStudents()
            call.respondText(
                student.toString(), status = HttpStatusCode.OK
            )

        }
        catch (e: Throwable) {
            call.respondText("${e.message}", status = HttpStatusCode.Unauthorized)
        }

    }

    delete("st/{id}"){
        val  id = call.parameters["id"] ?: return@delete call.respondText(

            "Missing or malformed id",
            status = HttpStatusCode.BadRequest

        )

        val result = db.deleteStudentById(id.toLong())
        try {
            if(result == 1){
                call.respondText(
                    "Student deleted successfully",
                    status = HttpStatusCode.OK
                )
            }


        }
        catch (e: Throwable){
            call.respondText("${e.message}", status = HttpStatusCode.OK)
        }
    }


    }

    

