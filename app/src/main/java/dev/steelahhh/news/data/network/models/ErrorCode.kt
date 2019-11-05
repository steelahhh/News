package dev.steelahhh.news.data.network.models

import com.squareup.moshi.Json
import dev.steelahhh.news.data.network.models.ApiResponse.ErrorResponse
import dev.steelahhh.news.data.network.models.ApiResponse.ErrorResponse.ApiKeyDisabled
import dev.steelahhh.news.data.network.models.ApiResponse.ErrorResponse.ApiKeyExhausted
import dev.steelahhh.news.data.network.models.ApiResponse.ErrorResponse.ApiKeyInvalid
import dev.steelahhh.news.data.network.models.ApiResponse.ErrorResponse.ApiKeyMissing
import dev.steelahhh.news.data.network.models.ApiResponse.ErrorResponse.ParameterInvalid
import dev.steelahhh.news.data.network.models.ApiResponse.ErrorResponse.ParametersMissing
import dev.steelahhh.news.data.network.models.ApiResponse.ErrorResponse.RateLimited
import dev.steelahhh.news.data.network.models.ApiResponse.ErrorResponse.SourceDoesNotExist
import dev.steelahhh.news.data.network.models.ApiResponse.ErrorResponse.SourcesTooMany
import dev.steelahhh.news.data.network.models.ApiResponse.ErrorResponse.UnexpectedError
import kotlin.reflect.KClass

enum class ErrorCode constructor(
    val derivedClass: KClass<out ErrorResponse>
) {
    @Json(name = "apiKeyDisabled")
    API_KEY_DISABLED(ApiKeyDisabled::class),

    @Json(name = "apiKeyExhausted")
    API_KEY_EXHAUSTED(ApiKeyExhausted::class),

    @Json(name = "apiKeyInvalid")
    API_KEY_INVALID(ApiKeyInvalid::class),

    @Json(name = "apiKeyMissing")
    API_KEY_MISSING(ApiKeyMissing::class),

    @Json(name = "parameterInvalid")
    PARAMETER_INVALID(ParameterInvalid::class),

    @Json(name = "parametersMissing")
    PARAMETERS_MISSING(ParametersMissing::class),

    @Json(name = "rateLimited")
    RATE_LIMITED(RateLimited::class),

    @Json(name = "sourcesTooMany")
    SOURCES_TOO_MANY(SourcesTooMany::class),

    @Json(name = "sourceDoesNotExist")
    SOURCE_DOES_NOT_EXIST(SourceDoesNotExist::class),

    @Json(name = "unexpectedError")
    UNEXPECTED_ERROR(UnexpectedError::class)
}
