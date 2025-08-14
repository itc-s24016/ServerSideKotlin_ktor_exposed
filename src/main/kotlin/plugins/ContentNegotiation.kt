package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.plugins

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

@OptIn(ExperimentalSerializationApi::class)
fun Application.configureContentNegotiation() {
    install(ContentNegotiation) {
        json(Json {
            //受信したJSONに存在しないプロパティがあってもエラーを発生させない
            ignoreUnknownKeys = true
            //nullの値を持つプロパティを含めない
            explicitNulls = false
            //プロパティ名をスネークケースに変換
            namingStrategy = JsonNamingStrategy.SnakeCase
            //JSONのフォーマットを整形して出力
            prettyPrint = true
        })
    }
}