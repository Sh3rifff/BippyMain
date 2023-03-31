package az.sharif.bippyteam.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import az.sharif.bippyteam.R
import az.sharif.bippyteam.adpater.NewsAdapter
import az.sharif.bippyteam.model.Article
import az.sharif.bippyteam.model.Headline
import az.sharif.bippyteam.service.CountryAPI
import az.sharif.bippyteam.service.CountryApiService
import az.sharif.bippyteam.viewmodel.NewsViewModel
import com.google.api.Distribution.BucketOptions.Linear
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsFragment:Fragment() {
    private val BASE_URL="https://newsapi.org/v2/"
    private lateinit var articleModel:ArrayList<Article>
    private lateinit var recyclerViewAdapter:NewsAdapter
    lateinit var countryApiService:CountryApiService
    lateinit var recyclerView: RecyclerView

    private lateinit var viewModel:NewsViewModel
    private val newsAdapter=NewsAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView=view.findViewById(R.id.recyclerView)


        viewModel=ViewModelProvider(this)[NewsViewModel::class.java]
        viewModel.refreshData()

        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=newsAdapter


        //loadData()

        observeLiveData()

    }


    private fun observeLiveData(){
        viewModel.articles.observe(viewLifecycleOwner, Observer { articles ->
            articles?.let {
                recyclerView.visibility=View.VISIBLE
                recyclerViewAdapter.updateArticleList(articles)
            }
        })

        viewModel.articleError.observe(viewLifecycleOwner, Observer { error->
            error?.let {
                if(it){
                    view?.findViewById<TextView>(R.id.articleError)?.visibility=View.VISIBLE
                    recyclerView.visibility=View.GONE

                }
                else {
                    view?.findViewById<TextView>(R.id.articleError)?.visibility=View.GONE

                }
            }
        })

        viewModel.articleLoading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let{
                if(it){
                    view?.findViewById<ProgressBar>(R.id.articleLoading)?.visibility=View.VISIBLE
                    recyclerView.visibility=View.GONE
                    view?.findViewById<TextView>(R.id.articleError)?.visibility=View.GONE

                }
                else{
                    view?.findViewById<ProgressBar>(R.id.articleLoading)?.visibility=View.GONE
                }
            }
        })
    }


    /*fun loadData(){

        val retrofit=
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        val service= retrofit.create(CountryAPI::class.java)
        val call=service.getArticles()

        call.enqueue(object : Callback<Headline> {
            override fun onFailure(call: Call<Headline>, t: Throwable) {

                Toast.makeText(context,"${t.localizedMessage}",Toast.LENGTH_LONG).show()
                t.localizedMessage
            }

            override fun onResponse(
                call: Call<Headline>,
                response: Response<Headline>
            ) {
                if (response.isSuccessful){

                    response.body()?.let {
*//*
                        Toast.makeText(context,"Elaa",Toast.LENGTH_LONG).show()
*//*
                        articleModel=ArrayList(it.articles)

                        articleModel?.let {
                            recyclerViewAdapter=NewsAdapter(it)
                            view?.findViewById<RecyclerView>(R.id.recyclerView)?.adapter =recyclerViewAdapter
                        }

                    }
                }else     Toast.makeText(context,"Badd",Toast.LENGTH_LONG).show()

            }
        })
    }*/


}