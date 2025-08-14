package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.entity

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.table.Books
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.LongEntity
import org.jetbrains.exposed.v1.dao.LongEntityClass

class BookEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<BookEntity>(Books)

    var title by Books.title
    var author by Books.author
    var releaseDate by Books.releaseDate
    var isDeleted by Books.isDeleted
}