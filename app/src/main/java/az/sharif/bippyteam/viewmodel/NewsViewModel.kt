package az.sharif.bippyteam.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import az.sharif.bippyteam.model.Article
import az.sharif.bippyteam.model.Headline
import az.sharif.bippyteam.service.CountryApiService
import az.sharif.bippyteam.service.NewsDatabase
import az.sharif.bippyteam.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class NewsViewModel(application: Application):  BaseViewModel(application){

    private val articleApiService=CountryApiService()
    private val disposable= CompositeDisposable()
    private var customPreferences= CustomSharedPreferences(getApplication())
    private var refreshTime=10*60*1000*1000*1000L



    val articles= MutableLiveData<List<Article>>()
    val articleError= MutableLiveData<Boolean>()
    val articleLoading= MutableLiveData<Boolean>()

    fun refreshData(){
        val updateTime=customPreferences.getTime()
        if(updateTime!=null && updateTime!=0L && System.nanoTime()-updateTime<refreshTime){
            getDataFromSQLite()
        }
        else{
            getDataFromApi()
        }

    }

    fun refreshFromApi(){
        getDataFromApi()
    }

    private fun getDataFromSQLite(){
        launch {
            articleLoading.value=true
            val news=NewsDatabase(getApplication()).newsDao().getAllNews()
            showNews(news)
            Toast.makeText(getApplication(),"Countries From SQLite",Toast.LENGTH_LONG).show()

        }
    }


    private fun getDataFromApi(){

        articleLoading.value=true

        disposable.add(
            articleApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Headline>(){
                    override fun onSuccess(t: Headline) {
                        /*articles.value=t.articles
                        articleError.value=false
                        articleLoading.value=false*/

                        storeInSQLite(t.articles)
                        Toast.makeText(getApplication(),"Countries From API",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        articleError.value=true
                        articleLoading.value=false
                        e.printStackTrace()

                    }


                })
        )
    }


    private fun showNews(newsList:List<Article>){
        articles.value=newsList
        articleError.value=false
        articleLoading.value=false
    }


    private fun storeInSQLite(list: List<Article>){

        launch {
            val dao=NewsDatabase(getApplication()).newsDao()
            dao.deleteAllNews()
            val listLong=dao.insertAll(*list.toTypedArray())
            var i=0
            while(i<list.size){
                list[i].uuid=listLong[i].toInt()
                i+=1
            }
            showNews(list)
        }

        customPreferences.saveTime(System.nanoTime())

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}