package az.sharif.bippyteam.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import az.sharif.bippyteam.R
import az.sharif.bippyteam.adpater.NewsAdapter
import az.sharif.bippyteam.model.Article
import az.sharif.bippyteam.viewmodel.NewsViewModel
import java.util.*
import kotlin.collections.ArrayList

class NewsFragment:Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var refreshLayout:SwipeRefreshLayout

    private lateinit var viewModel:NewsViewModel
    private val newsAdapter=NewsAdapter(arrayListOf())
    private lateinit var searchView: SearchView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView=view.findViewById(R.id.recyclerView)
        refreshLayout=view.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        searchView=view.findViewById<SearchView>(R.id.searchView)



        viewModel=ViewModelProvider(this)[NewsViewModel::class.java]
        viewModel.refreshData()

        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=newsAdapter



        refreshLayout.setOnRefreshListener {
            recyclerView.visibility=View.GONE
            view.findViewById<TextView>(R.id.articleError).visibility=View.GONE
            //view.findViewById<ProgressBar>(R.id.progressBar).visibility=View.VISIBLE
            viewModel.refreshData()
            refreshLayout.isRefreshing=false
        }

        observeLiveData()


        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                filterList(p0)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                filterList(p0)
                return true
            }

        })



    }

    private fun filterList(query:String?){
        if(query!=null){
            val filteredList= ArrayList<Article>()
            for(i in viewModel.articles.value!!){
                if(i.title?.lowercase(Locale.ROOT)!!.contains(query)){
                    filteredList.add(i)
                }
            }

            if(filteredList.isEmpty()){
                //Toast.makeText(context,"No Data Found",Toast.LENGTH_LONG).show()
            }else{
                newsAdapter.setFilteredList(filteredList)
            }

        }
        else Toast.makeText(context,"Query is null",Toast.LENGTH_LONG).show()
    }


    private fun observeLiveData(){
        viewModel.articles.observe(viewLifecycleOwner, Observer { articles ->
            articles?.let {
                recyclerView.visibility=View.VISIBLE
                newsAdapter.updateArticleList(articles)
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




}