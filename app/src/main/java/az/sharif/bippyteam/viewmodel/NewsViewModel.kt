package az.sharif.bippyteam.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import az.sharif.bippyteam.model.Article
import az.sharif.bippyteam.model.Headline
import az.sharif.bippyteam.service.CountryApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class NewsViewModel(application: Application):  BaseViewModel(application){

    private val articleApiService=CountryApiService()
    private val disposable= CompositeDisposable()

    val articles= MutableLiveData<List<Article>>()
    val articleError= MutableLiveData<Boolean>()
    val articleLoading= MutableLiveData<Boolean>()

    fun refreshData(){
        getDataFromApi()

    }

    private fun getDataFromApi(){

        articleLoading.value=true

        disposable.add(
            articleApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Headline>(){
                    override fun onSuccess(t: Headline) {
                        articles.value=t.articles
                        articleError.value=false
                        articleLoading.value=false
                    }

                    override fun onError(e: Throwable) {
                        articleError.value=true
                        articleLoading.value=false
                        e.printStackTrace()

                    }


                })
        )
    }
}