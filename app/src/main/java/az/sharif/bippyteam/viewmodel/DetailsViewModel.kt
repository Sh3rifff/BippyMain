package az.sharif.bippyteam.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import az.sharif.bippyteam.model.Article
import az.sharif.bippyteam.service.NewsDatabase
//import az.sharif.bippyteam.service.NewsDatabase
import kotlinx.coroutines.launch

open class DetailsViewModel(application: Application):BaseViewModel(application) {
    val countryLiveData= MutableLiveData<Article>()

    fun getDataFromRoom(uuid: Int){
        launch {
            val dao= NewsDatabase(getApplication()).newsDao()
            val country= dao.getNews(uuid)
            countryLiveData.value=country

        }
    }
}