package az.sharif.bippyteam.viewmodel

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import az.sharif.bippyteam.data.repository.UserRepository
import az.sharif.bippyteam.model.MyUsers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):BaseViewModel(application) {

    private val repos by lazy { UserRepository(application.applicationContext) }
    var identify = MutableLiveData<Boolean>()
    var autoLog = MutableLiveData<Boolean>()

    fun getUserFromLocal(name:String, pass:String) {
        var currentuser: MyUsers?
        viewModelScope.launch {
           currentuser  = repos.validateUser(name,pass)
            if (currentuser!=null){
                identify.value=true
            }
        }
    }

    fun saveUser(myUsers: MyUsers){ viewModelScope.launch {repos.saveUserToLocal(myUsers) } }
    
    
    //for Debugging
    fun getAllUsersFromLocal(){ viewModelScope.launch { val list = repos.getAllUsers() } }

    
///////// For log out
     fun clearAll(){ viewModelScope.launch { repos.clearAllUser() } }
    
    
    fun autoLogin(){
        viewModelScope.launch {
            val list = repos.autoLogin()
            autoLog.value = list?.isEmpty()
            Log.d("bla", "autoLogin: ${autoLog.value}")
        }
    }
}