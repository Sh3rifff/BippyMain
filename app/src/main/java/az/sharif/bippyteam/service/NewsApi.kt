package az.sharif.bippyteam.service

import az.sharif.bippyteam.model.Headline
import io.reactivex.Single
import retrofit2.http.GET

interface NewsApi {
    /*<!--   https://newsapi.org/v2/   -->
    <!--   top-headlines?country=us&apiKey=cb783fc0425c4c71bf69e4a0bb4631c7   -->*/

    @GET("top-headlines?country=us&apiKey=cb783fc0425c4c71bf69e4a0bb4631c7")
    fun getArticles(): Single<Headline>
}