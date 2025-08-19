package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.entity

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.table.*
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.ImmutableEntityClass
import org.jetbrains.exposed.v1.dao.LongEntity
import org.jetbrains.exposed.v1.dao.LongEntityClass

// 最新のレンタルログを書籍IDから取得する
class BookWithRentalLogEntity(id: EntityID<Long>) : LongEntity(id) {
//    companion object : LongEntityClass<BookWithRentalLogEntity>(Books)
    companion object : ImmutableEntityClass<Long, BookWithRentalLogEntity>(Books)


    val title by Books.title
    val author by Books.author
    val releaseDate by Books.releaseDate

    // rentalLogsプロパティは、RentalLogEntityを参照することで
    // 指定した書籍IDに関連するレンタルログを全て取得できる
    val rentalLogs by RentalLogEntity referrersOn RentalLogs.book orderBy RentalLogs.rentalDatetime

    // 最新のレンタルログを指定する
    val currentLog: RentalLogEntity?
        get() = rentalLogs.firstOrNull { it.returnDatetime == null }
}