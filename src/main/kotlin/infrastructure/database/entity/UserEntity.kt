package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.entity

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.table.Users
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.LongEntity
import org.jetbrains.exposed.v1.dao.LongEntityClass

class UserEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserEntity>(Users)

    var email by Users.email
    var password by Users.password
    var name by Users.name
    var roleType by Users.roleType
}