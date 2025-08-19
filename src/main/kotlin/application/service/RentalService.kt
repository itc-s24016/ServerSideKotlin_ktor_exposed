package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.application.service

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.RentalInfo
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository.BookRepository
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository.RentalRepository
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository.UserRepository
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime

private const val RENTAL_TERM_DAYS = 14
private val JST = TimeZone.of("Asia/Tokyo")

class RentalService(
    private val rentalRepository: RentalRepository,
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository,
) {

    fun startRental(bookId: Long, userId: Long): RentalInfo {
        // ユーザーIDチェック。存在しないユーザーならエラー
        val user = userRepository.find(userId)
            ?: throw IllegalArgumentException("User with id $userId doesn't exist")

        // 書籍IDチェック。存在しない書籍ならエラー
        val bookWithRental = bookRepository.findWithRental(bookId)
            ?: throw IllegalArgumentException("Book with id $bookId doesn't exist")

        // すでに貸出中ならエラー
        if (bookWithRental.isRental) {
            throw IllegalStateException("Book ${bookWithRental.book.title} is currently on rental.")
        }

        val rentalDatetime = getTimestamp()
        val returnDeadline = getTimestamp(RENTAL_TERM_DAYS)
        val rentalInfo = RentalInfo(
            id = 0, // IDは自動生成されるため、0を指定
            book = bookWithRental.book,
            user = user,
            rentalDatetime = rentalDatetime,
            returnDeadline = returnDeadline,
        )
        return rentalRepository.startRental(rentalInfo)
    }

    fun endRental(id: Long, userId: Long): RentalInfo {
        // 貸出IDチェック。存在しない貸出ならエラー
        val rentalInfo = rentalRepository.find(id)
            ?: throw IllegalArgumentException("Rental with id $id doesn't exist")

        // ユーザーIDチェック。存在しないユーザーならエラー
        val user = userRepository.find(userId)
            ?: throw IllegalArgumentException("User with id $userId doesn't exist")

        // 貸出中かチェック
        if (rentalInfo.returnDatetime != null) {
            throw IllegalStateException("Rental with id $id is not currently active.")
        }

        // 貸出中のユーザーと異なるユーザーが返却しようとした場合はエラー
        if (rentalInfo.user.id != user.id) {
            throw IllegalStateException("Rental with id $id is not owned by user with id $userId")
        }

        // 返却日時を現在日時に設定した新しい RentalInfo を元の RentalInfo を複製して作成
        val updateRental = rentalInfo.copy(
            returnDatetime = getTimestamp()
        )
        // 貸出を終了
        return rentalRepository.endRental(updateRental)
    }

    @OptIn(ExperimentalTime::class)
    private fun getTimestamp(delta: Int = 0): LocalDateTime {
        val now = Clock.System.now()
        return now.plus(delta.days).toLocalDateTime(JST)
    }
}