package dev.steelahhh.news.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.steelahhh.news.data.entities.ArticleEntity

@Database(
    entities = [
        ArticleEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
}
