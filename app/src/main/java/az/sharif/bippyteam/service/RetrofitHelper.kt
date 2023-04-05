package az.sharif.bippyteam.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val BASE_URL = "https://raw.githubusercontent.com/"
    private const val BASE_URL2 = "https://raw.githubusercontent.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: DiscoveryAPI = retrofit.create(DiscoveryAPI::class.java)


    private val retrofit2 = Retrofit.Builder()
        .baseUrl(BASE_URL2)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val category: DiscoveryAPI = retrofit2.create(DiscoveryAPI::class.java)
}