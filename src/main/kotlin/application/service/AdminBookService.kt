package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.application.service

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.Book
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository.BookRepository
import kotlinx.datetime.LocalDate

/**
 * 書籍の管理(登録・更新・削除)を行うサービスクラス
 */
class AdminBookService(
    private val repository: BookRepository,
) {
    fun register(book: Book): Book {
        return repository.register(book)
    }

    fun update(id: Long, title: String? = null, author: String? = null, releaseDate: LocalDate? = null): Book {
        return repository.update(id, title, author, releaseDate)
    }

    fun delete(id: Long) {
        return repository.delete(id)
    }
}