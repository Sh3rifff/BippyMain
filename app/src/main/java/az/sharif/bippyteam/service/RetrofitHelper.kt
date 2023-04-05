package az.sharif.bippyteam.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val BASE_URL = "https://raw.githubusercontent.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: DiscoveryAPI = retrofit.create(DiscoveryAPI::class.java)
}