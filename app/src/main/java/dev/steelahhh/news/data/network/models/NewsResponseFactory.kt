@file:Suppress("UNCHECKED_CAST")

package dev.steelahhh.news.data.network.models

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonAdapter.Factory
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dev.steelahhh.news.data.network.models.ApiResponse.ErrorResponse
import dev.steelahhh.news.data.network.models.ApiResponse.SuccessResponse
import java.lang.reflect.Type

/**
 * Transform all the possible responses from NewsApi to a [ApiResponse]
 */
class NewsResponseFactory : Factory {
    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        val klass = Types.getRawType(type)
        if (ApiResponse::class.java != klass) {
            return null
        }
        return createJsonAdapter(moshi)
    }

    private fun createJsonAdapter(moshi: Moshi) = object : JsonAdapter<ApiResponse>() {
        override fun fromJson(reader: JsonReader): ApiResponse? {
            val json = reader.readJsonValue()

            val value = json as Map<String, Any>
            val status = value["status"] as? String
                ?: throw JsonDataException("Invalid status received")
            return when (status) {
                // if response is ok, just decode from json
                "ok" -> moshi.adapter(SuccessResponse::class.java).fromJsonValue(json)
                "error" -> {
                    // if error, try to find a code that matches the response
                    val errorResponseClass = value["code"]?.let {
                        moshi.adapter(ErrorCode::class.java).fromJson("\"${it as String}\"")
                    }?.derivedClass?.java ?: throw JsonDataException("No error code received")
                    moshi.adapter(errorResponseClass).fromJsonValue(value)
                }
                else -> throw JsonDataException("Unknown status received")
            }
        }

        override fun toJson(writer: JsonWriter, value: ApiResponse?) {
            when (value) {
                is SuccessResponse -> moshi
                    .adapter(SuccessResponse::class.java)
                    .toJson(writer, value)
                is ErrorResponse -> {
                    val adapter =
                        moshi.adapter(value.code.derivedClass.java) as JsonAdapter<ErrorResponse>
                    adapter.toJson(writer, value)
                }
            }
        }
    }
}
