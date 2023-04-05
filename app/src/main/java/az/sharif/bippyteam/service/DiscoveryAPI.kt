package az.sharif.bippyteam.service

import az.sharif.bippyteam.model.ServiceModel
import retrofit2.Response
import retrofit2.http.GET

interface DiscoveryAPI {

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    suspend fun getData(): Response<List<ServiceModel>>

}