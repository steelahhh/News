package dev.steelahhh.news.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.steelahhh.news.data.entities.NewsEntity

@Dao
interface NewsDao {
    @Query("select * from news where id=:id")
    suspend fun getById(id: Int): NewsEntity

    @Query("select * from news where title=:title")
    suspend fun getByTitle(title: String): NewsEntity

    @Insert
    suspend fun putNews(news: List<NewsEntity>)

    @Query("delete from news")
    suspend fun clear()
}
