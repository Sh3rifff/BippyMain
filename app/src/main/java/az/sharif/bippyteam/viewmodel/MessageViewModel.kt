package az.sharif.bippyteam.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import az.sharif.bippyteam.model.Article
import az.sharif.bippyteam.model.Message

class MessageViewModel(application: Application):BaseViewModel(application) {

    val messages= MutableLiveData<List<Message>>()

    private fun addUser(){
        var user=Message("Qurban","Yaxsi Olasan","https://cdn-icons-png.flaticon.com/512/3135/3135715.png")
        messages.value= listOf(user)
    }

}