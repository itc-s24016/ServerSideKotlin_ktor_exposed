package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.UserPrincipal

interface UserRepository {
    fun find(email: String): UserPrincipal?
}