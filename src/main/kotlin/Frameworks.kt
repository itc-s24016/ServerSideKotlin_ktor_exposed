package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager

import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.di.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import java.sql.Connection
import java.sql.DriverManager
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

fun Application.configureFrameworks() {
    dependencies {
        provide { GreetingService { "Hello, World!" } }
    }
}
