package dev.steelahhh.news.data.network.models

import com.squareup.moshi.JsonClass

sealed class ApiResponse(val status: String) {

    @JsonClass(generateAdapter = true)
    data class SuccessResponse(
        val totalResults: Int,
        val articles: List<ArticleResponse>
    ) : ApiResponse("ok")

    sealed class ErrorResponse : ApiResponse("error") {
        abstract val code: ErrorCode
        abstract val message: String

        @JsonClass(generateAdapter = true)
        internal data class MissingApiKey(
            override val code: ErrorCode,
            override val message: String
        ) : ErrorResponse()

        /**
         * Your API key has been disabled.
         */
        @JsonClass(generateAdapter = true)
        internal data class ApiKeyDisabled(
            override val code: ErrorCode,
            override val message: String
        ) : ErrorResponse()

        /**
         * Your API key has no more requests available.
         */
        @JsonClass(generateAdapter = true)
        internal data class ApiKeyExhausted(
            override val code: ErrorCode,
            override val message: String
        ) : ErrorResponse()

        /**
         * Your API key hasn't been entered correctly. Double check it and try again.
         */
        @JsonClass(generateAdapter = true)
        internal data class ApiKeyInvalid(
            override val code: ErrorCode,
            override val message: String
        ) : ErrorResponse()

        /**
         * Your API key is missing from the request. Append it to the request with one of these methods.
         */
        @JsonClass(generateAdapter = true)
        internal data class ApiKeyMissing(
            override val code: ErrorCode,
            override val message: String
        ) : ErrorResponse()

        /**
         * You've included a parameter in your request which is currently not supported. Check the message property for more details.
         */
        @JsonClass(generateAdapter = true)
        internal data class ParameterInvalid(
            override val code: ErrorCode,
            override val message: String
        ) : ErrorResponse()

        /**
         * Required parameters are missing from the request and it cannot be completed. Check the message property for more details.
         */
        @JsonClass(generateAdapter = true)
        internal data class ParametersMissing(
            override val code: ErrorCode,
            override val message: String
        ) : ErrorResponse()

        /**
         * You have been rate limited. Back off for a while before trying the request again.
         */
        @JsonClass(generateAdapter = true)
        internal data class RateLimited(
            override val code: ErrorCode,
            override val message: String
        ) : ErrorResponse()

        /**
         * You have requested too many sources in a single request. Try splitting the request into 2 smaller requests.
         */
        @JsonClass(generateAdapter = true)
        internal data class SourcesTooMany(
            override val code: ErrorCode,
            override val message: String
        ) : ErrorResponse()

        /**
         * You have requested a source which does not exist.
         */
        @JsonClass(generateAdapter = true)
        internal data class SourceDoesNotExist(
            override val code: ErrorCode,
            override val message: String
        ) : ErrorResponse()

        /**
         * This shouldn't happen, and if it does then it's our fault, not yours. Try the request again shortly.
         */
        @JsonClass(generateAdapter = true)
        internal data class UnexpectedError(
            override val code: ErrorCode,
            override val message: String
        ) : ErrorResponse()
    }
}
