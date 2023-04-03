package az.sharif.bippyteam.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import az.sharif.bippyteam.R
import az.sharif.bippyteam.adpater.NewsAdapter
import az.sharif.bippyteam.viewmodel.NewsViewModel

class NewsFragment:Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var refreshLayout:SwipeRefreshLayout

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
        refreshLayout=view.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)


        viewModel=ViewModelProvider(this)[NewsViewModel::class.java]
        viewModel.refreshData()

        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=newsAdapter


        refreshLayout.setOnRefreshListener {
            recyclerView.visibility=View.GONE
            view.findViewById<TextView>(R.id.articleError).visibility=View.GONE
            //view.findViewById<ProgressBar>(R.id.progressBar).visibility=View.VISIBLE
            viewModel.refreshFromApi()
            refreshLayout.isRefreshing=false
        }

        observeLiveData()

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