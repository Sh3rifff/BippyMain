package az.sharif.bippyteam.view.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import az.sharif.bippyteam.R
import az.sharif.bippyteam.util.downloadFromUrl
import az.sharif.bippyteam.util.placeHolderProgressBar
import az.sharif.bippyteam.viewmodel.DetailsViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetailsFragment:Fragment() {

    private lateinit var viewModel: DetailsViewModel
    private var newsUuid=0
    private lateinit var sourceName: TextView
    private lateinit var detailsTitle:TextView
    private lateinit var desc:TextView
    private lateinit var image: ImageView
    private lateinit var webView: WebView
    private lateinit var date:TextView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sourceName=view.findViewById(R.id.tvSourceDetails)
        detailsTitle=view.findViewById(R.id.tvTitleDetails)
        desc=view.findViewById(R.id.tvDescDetails)
        image=view.findViewById(R.id.imageView)
        webView=view.findViewById(R.id.webView)
        date=view.findViewById(R.id.tvDate)

        arguments?.let {
            newsUuid=DetailsFragmentArgs.fromBundle(it).uuId

        }
        viewModel= ViewModelProvider(this)[DetailsViewModel::class.java]
        viewModel.getDataFromRoom(newsUuid)
        observeLiveData()
    }
    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun observeLiveData(){
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer { news->
            news?.let {
                detailsTitle.text=news.title
                sourceName.text=news.source?.name
                desc.text=news.description

                val formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                val localDate= LocalDate.parse(news.publishedAt,formatter)

                date.text=localDate.toString()


                context?.let {
                    image.downloadFromUrl(news.urlToImage, placeHolderProgressBar(it))
                }

                webView.settings.javaScriptEnabled = true

                webView.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        view?.loadUrl(news.url.toString())
                        return true
                    }
                }
                webView.loadUrl(news.url.toString())
            }

        })
    }

}


