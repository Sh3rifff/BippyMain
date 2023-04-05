package az.sharif.bippyteam.service.api

import az.sharif.bippyteam.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api/users")
    suspend fun getUser(@Query("name") name:String, @Query("pass") pass:String):User?
}