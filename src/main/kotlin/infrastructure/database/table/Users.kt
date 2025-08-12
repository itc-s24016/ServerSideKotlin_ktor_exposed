package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.table

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.types.RoleType
import org.jetbrains.exposed.v1.core.dao.id.LongIdTable

object Users : LongIdTable("users") {
    val email = varchar("email", 256).uniqueIndex()  //同じメアドで複数登録できないようにする
    val password = varchar("password", 128) // パスワードはハッシュ化して保存することを想定
    val name = varchar("name", 32) // ユーザー名
    val roleType = enumeration<RoleType>("role_type")  //RoleType型の列を定義する
}