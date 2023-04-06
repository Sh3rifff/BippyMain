package az.sharif.bippyteam.util

import az.sharif.bippyteam.util.Constants.NEWS_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NewsRetrofitInstance {
    val NEWSINSTANCE by lazy {
       Retrofit.Builder()
           .baseUrl(NEWS_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .build()
    }

}