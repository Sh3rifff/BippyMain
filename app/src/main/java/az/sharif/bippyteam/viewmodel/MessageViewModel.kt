package az.sharif.bippyteam.viewmodel

import android.app.Application
import az.sharif.bippyteam.model.Message

class MessageViewModel(application: Application):BaseViewModel(application) {

    var user=Message("Qurban","Yaxsi Olasan","https://cdn-icons-png.flaticon.com/512/3135/3135715.png")


}