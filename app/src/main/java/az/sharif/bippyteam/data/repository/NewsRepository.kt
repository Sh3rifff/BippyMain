package az.sharif.bippyteam.data.repository

import az.sharif.bippyteam.model.Article
import az.sharif.bippyteam.model.Headline
import az.sharif.bippyteam.service.api.NewsApi
import az.sharif.bippyteam.util.NewsRetrofitInstance

class NewsRepository {

    private val newsRetrofit:NewsApi = NewsRetrofitInstance.NEWSINSTANCE.create(NewsApi::class.java)

    suspend fun getNewsResponse():List<Article>{
        val response =newsRetrofit.getArticles()
        if(response.isSuccessful){
            return response.body()!!.articles
        }
        println("Error occurred")
        return emptyList()
    }

}