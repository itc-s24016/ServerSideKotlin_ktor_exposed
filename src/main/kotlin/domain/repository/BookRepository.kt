package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.Book
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.BookWithRental
import kotlinx.datetime.LocalDate

/*
 * BookRepositoryは、書籍データの管理を行うためのインターフェースです。
 * このインターフェースは、書籍の登録、更新、削除、およびレンタル情報を統合した書籍情報の取得を提供します。
 */
interface BookRepository {
    fun findAllWithRental(): List<BookWithRental>
    fun findWithRental(id: Long): BookWithRental?
    fun register(book: Book): Book
    fun update(id: Long, title: String? = null, author: String? = null, releaseDate: LocalDate? = null): Book
    fun delete(id: Long)
}