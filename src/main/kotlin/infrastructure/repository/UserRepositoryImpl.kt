package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.repository

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.UserPrincipal
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository.UserRepository
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.entity.UserEntity
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.table.Users
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

class UserRepositoryImpl : UserRepository {
    override fun find(email: String): UserPrincipal? {
        return transaction {
            UserEntity.find{
                Users.email eq email
            }.singleOrNull()?.let(::toModel)
        }
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
}