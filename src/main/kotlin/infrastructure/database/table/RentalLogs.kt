package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.table

import org.jetbrains.exposed.v1.core.dao.id.LongIdTable
import org.jetbrains.exposed.v1.datetime.datetime

object RentalLogs : LongIdTable("rental_logs") {
    val book = reference("book_id", Books)//外部キー制約を付ける データベース名：book_id　プログラム変数名：book
    val user = reference("user_id", Users)//　　　　〃　　　　　 データベース名：user_id　プログラム変数名：user
    val rentalDatetime = datetime("rental_datetime")//貸出日時
    val returnDeadline = datetime("rental_deadline")//返却期限
    val returnDatetime = datetime("return_datetime").nullable()//返却日時 ※貸出中の場合のためにnull許容型にしておく
}