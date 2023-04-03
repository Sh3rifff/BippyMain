package az.sharif.bippyteam.service

import az.sharif.bippyteam.model.Headline
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiService {

    /*<!--   https://newsapi.org/v2/   -->
    <!--   top-headlines?country=us&apiKey=cb783fc0425c4c71bf69e4a0bb4631c7   -->*/

    private val BASE_URL="https://newsapi.org/v2/"
    private val api= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(NewsApi::class.java)

    fun getData(): Single<Headline> {
        return api.getArticles()
    }
}