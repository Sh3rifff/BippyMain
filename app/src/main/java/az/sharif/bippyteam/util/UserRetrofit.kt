package az.sharif.bippyteam.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserRetrofit {

    val USERINSTANCE by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.NEWS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}