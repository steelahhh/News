package dev.steelahhh.news.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)
