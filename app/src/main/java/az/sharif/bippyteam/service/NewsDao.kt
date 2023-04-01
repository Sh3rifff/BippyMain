package az.sharif.bippyteam.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import az.sharif.bippyteam.model.Article


@Dao
interface NewsDao {
    @Insert
    suspend fun insertAll(vararg articles: Article): List<Long>


    @Query("SELECT * FROM article")
    suspend fun getAllNews():List<Article>

    @Query("SELECT * FROM article WHERE uuid= :countryId")
    suspend fun getNews(newsId: Int): Article

    @Query("DELETE FROM article")
    suspend fun deleteAllNews()
}
