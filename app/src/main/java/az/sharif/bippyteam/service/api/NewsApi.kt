package az.sharif.bippyteam.service.api

import az.sharif.bippyteam.model.Headline
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {

    @GET("top-headlines?country=us&apiKey=cb783fc0425c4c71bf69e4a0bb4631c7")
    suspend fun getArticles():Response<Headline>
}