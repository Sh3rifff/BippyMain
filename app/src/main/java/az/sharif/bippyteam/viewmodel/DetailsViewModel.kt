package az.sharif.bippyteam.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import az.sharif.bippyteam.model.Article
import az.sharif.bippyteam.service.database.NewsDatabase
import kotlinx.coroutines.launch

open class DetailsViewModel(application: Application):BaseViewModel(application) {
    val newsLiveData= MutableLiveData<Article>()

    fun getDataFromRoom(uuid: Int){
        launch {
            val dao= NewsDatabase(getApplication()).newsDao()
            val news= dao.getNews(uuid)
            newsLiveData.value=news

        }
    }
}