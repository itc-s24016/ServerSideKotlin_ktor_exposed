package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model

// 貸出中の本と貸出情報を保持するデータクラス
data class BookWithRental(
    val book: Book,
    val rental: RentalWithUser?
){
    // 貸出中か判定する
    // rental が true なら貸出中
    val isRental: Boolean
        get() = rental != null
}
