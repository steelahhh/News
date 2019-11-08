package dev.steelahhh.news.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.steelahhh.news.data.entities.ArticleEntity

@Dao
interface NewsDao {
    @Query("select * from news where id=:id")
    suspend fun getById(id: Int): ArticleEntity

    /**
     * Get the article by title, since there are no IDs in the network response
     */
    @Query("select * from news where title=:title")
    suspend fun getByTitle(title: String): ArticleEntity

    @Insert
    suspend fun putNews(articles: List<ArticleEntity>)

    @Query("delete from news")
    suspend fun clear()
}
