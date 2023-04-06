package az.sharif.bippyteam.data.repository

import android.content.Context
import az.sharif.bippyteam.model.MyUsers
import az.sharif.bippyteam.service.api.UserApi
import az.sharif.bippyteam.service.database.UserDatabase
import az.sharif.bippyteam.util.UserRetrofit

class UserRepository(context: Context) {
    private fun userCall():UserApi  =  UserRetrofit.USERINSTANCE.create(UserApi::class.java)
    private val userDao by lazy {
            UserDatabase.getInstance(context).userDao()
    }

    suspend fun getUserCredR(name:String,pass:String):MyUsers?{
        return userCall().getUser(name,pass)!!
    }
    suspend fun saveUserToLocal(myUsers: MyUsers){
        userDao.saveUser(myUsers)
    }
    suspend fun validateUser(name:String,pass:String):MyUsers?{
        return userDao.validateUser(name,pass)
    }
    suspend fun getAllUsers():List<MyUsers>{
        return userDao.getAllUsers()
    }
}