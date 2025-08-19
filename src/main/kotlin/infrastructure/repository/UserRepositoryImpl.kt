package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.repository

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.User
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.UserPrincipal
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository.UserRepository
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.entity.UserEntity
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.table.Users
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

class UserRepositoryImpl(
    val userCompanion: UserEntity.Companion
) : UserRepository {
    override fun find(email: String): UserPrincipal? = transaction {
        userCompanion.find {
            Users.email eq email
        }.singleOrNull()?.let(::toModel)
    }

    override fun find(id: Long): User? = transaction {
        userCompanion.findById(id)?.let(::toSimpleModel)
    }

    private fun toModel(entity: UserEntity): UserPrincipal = entity.run {
        UserPrincipal(
            id = id.value,
            email = email,
            name = name,
            password = password,
            roleType = roleType
        )
    }

    private fun toSimpleModel(entity: UserEntity): User = entity.run {
        User(
            id = id.value,
            name = name
        )
    }
}