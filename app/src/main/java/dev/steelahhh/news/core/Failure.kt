package dev.steelahhh.news.core

import dev.steelahhh.news.data.network.models.ErrorCode

sealed class Failure {
    object NetworkConnection : Failure()
    object UnknownError : Failure()

    data class FeatureFailure(val error: ErrorCode) : Failure()
}
