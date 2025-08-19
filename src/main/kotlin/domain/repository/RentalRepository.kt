package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.RentalInfo

/*
 * RentalRepository はレンタル情報の取得と更新を行うリポジトリインターフェースです。
 */
interface RentalRepository{
    fun find(id: Long): RentalInfo?
    fun startRental(rental: RentalInfo): RentalInfo
    fun endRental(rental: RentalInfo): RentalInfo
}