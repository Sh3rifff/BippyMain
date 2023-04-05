package az.sharif.bippyteam.data.repository

import android.content.Context
import az.sharif.bippyteam.model.User
import az.sharif.bippyteam.service.api.UserApi
import az.sharif.bippyteam.service.database.UserDatabase
import az.sharif.bippyteam.util.UserRetrofit
import retrofit2.Retrofit

class UserRepository(context: Context) {
    private fun userCall():UserApi  =  UserRetrofit.USERINSTANCE.create(UserApi::class.java)
    private val userDao by lazy {
            UserDatabase.getInstance(context).userDao()
    }

    suspend fun getUserCred(name:String,pass:String):User?{
        return userCall().getUser(name,pass)!!
    }
}