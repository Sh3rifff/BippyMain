package az.sharif.bippyteam.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import az.sharif.bippyteam.model.Article

class NewsViewModel(application: Application):  BaseViewModel(application){

    val articles= MutableLiveData<List<Article>>()
    val articleError= MutableLiveData<Boolean>()
    val articleLoading= MutableLiveData<Boolean>()

    fun refreshData(){
        Toast.makeText(getApplication(),"DEneme",Toast.LENGTH_LONG).show()
    }
}