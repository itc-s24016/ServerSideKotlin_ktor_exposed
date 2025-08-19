package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.User
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.UserPrincipal

/*
 * UserRepository はユーザー情報の取得を行うリポジトリインターフェースです。
 */
interface UserRepository {
    fun find(email: String): UserPrincipal?
    fun find(id: Long): User?
}