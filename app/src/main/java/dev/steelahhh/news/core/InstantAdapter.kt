package dev.steelahhh.news.core

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.io.IOException
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime

class InstantAdapter : JsonAdapter<Instant>() {

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader) = reader.nextString().parseInstant()

    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, instant: Instant?) {
        writer.value(instant!!.toString())
    }

    private fun String.parseInstant() = if (!endsWith("Z")) {
        OffsetDateTime.parse(this).toInstant()
    } else {
        Instant.parse(this)
    }
}
