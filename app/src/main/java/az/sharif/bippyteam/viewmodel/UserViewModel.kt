package az.sharif.bippyteam.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import az.sharif.bippyteam.data.repository.UserRepository
import az.sharif.bippyteam.model.MyUsers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):BaseViewModel(application) {

    private val repos by lazy { UserRepository(application.applicationContext) }
   // lateinit var
    var identified = false


    fun getUserFromLocal(name:String, pass:String) {
        var currentuser:MyUsers?=null
        viewModelScope.launch {
           currentuser  = repos.validateUser(name,pass)
            if(currentuser!=null){
                identified=true
            }
        }
    }

    fun saveUser(myUsers: MyUsers){
        viewModelScope.launch {
            repos.saveUserToLocal(myUsers)
        }
    }
    fun getAllUsersFromLocal(){

        viewModelScope.launch {
            val list = repos.getAllUsers()
            println(list)
        }

    }
}