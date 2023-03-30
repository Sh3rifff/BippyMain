package az.sharif.bippyteam.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import az.sharif.bippyteam.R
import az.sharif.bippyteam.adpater.NewsAdapter
import az.sharif.bippyteam.model.Article
import az.sharif.bippyteam.model.Headline
import az.sharif.bippyteam.service.CountryAPI
import az.sharif.bippyteam.service.CountryApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsViewModel(application: Application):  BaseViewModel(application){



    val articles= MutableLiveData<List<Article>>()
    val articleError= MutableLiveData<Boolean>()
    val articleLoading= MutableLiveData<Boolean>()



}