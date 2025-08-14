package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.table

import org.jetbrains.exposed.v1.core.dao.id.LongIdTable
import org.jetbrains.exposed.v1.datetime.date
//書籍テーブルを定義する
object Books : LongIdTable("books") {
    val title = varchar("title", 128)// タイトル
    val author = varchar("author", 32)// 著者
    val releaseDate = date("release_date")// 発売日
    val isDeleted = bool("is_deleted").default(false) // 書籍が削除されているか(false:削除されていない)
}