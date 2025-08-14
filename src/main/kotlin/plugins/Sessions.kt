package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.sessions.SessionStorageMemory
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.cookie
import io.ktor.server.sessions.maxAge
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.UserPrincipal
import kotlin.time.Duration.Companion.hours

fun Application.configureSessions() {
    install(Sessions) {
        cookie<UserPrincipal>("bms", SessionStorageMemory()) {
            // Cookieの名前を指定 ("bms")
            // SessionStorageMemoryを使用してセッションをメモリに保存
            // Cookieの有効期限を1時間に設定
            cookie.path = "/"
            cookie.maxAge = 1.hours
        }
    }
}