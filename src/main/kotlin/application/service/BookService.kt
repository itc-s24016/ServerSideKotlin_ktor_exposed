package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.application.service

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.BookWithRental
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository.BookRepository

class BookService(
    private val repository: BookRepository
) {
    fun getList(): List<BookWithRental> {
        return repository.findAllWithRental()
    }

    fun getDetail(id: Long): BookWithRental? {
        return repository.findWithRental(id)
    }
}