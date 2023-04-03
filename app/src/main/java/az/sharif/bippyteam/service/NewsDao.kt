package az.sharif.bippyteam.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import az.sharif.bippyteam.model.Article
import az.sharif.bippyteam.model.Headline

/*

@Dao
interface NewsDao {
    @Insert
    suspend fun insertAll(vararg articles: Article): List<Long>


    @Query("SELECT * FROM article")
    suspend fun getAllNews():Headline

    @Query("SELECT * FROM article WHERE uuid= :newsId")
    suspend fun getNews(newsId: Int): Article

    @Query("DELETE FROM article")
    suspend fun deleteAllNews()
}
*/
