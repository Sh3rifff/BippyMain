package az.sharif.bippyteam.adpater

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import az.sharif.bippyteam.R
import az.sharif.bippyteam.model.Article
import az.sharif.bippyteam.util.downloadFromUrl
import az.sharif.bippyteam.util.placeHolderProgressBar
import az.sharif.bippyteam.view.fragment.NewsFragmentDirections
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class NewsAdapter(val articleList: ArrayList<Article>): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(var view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.item_layout,parent,false)
        return ArticleViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {





        holder.view.findViewById<TextView>(R.id.tvTitle).text=articleList[position].title
        holder.view.findViewById<TextView>(R.id.tvSource).text=articleList[position].source?.name



        val date=articleList[position].publishedAt
        val formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val localDate=LocalDate.parse(date,formatter)
        //Toast.makeText(holder.view.context,localDate.toString(),Toast.LENGTH_LONG).show()

        holder.view.findViewById<TextView>(R.id.tvDate).text=localDate.toString()




        holder.view.setOnClickListener{
            val action=NewsFragmentDirections.actionNewsFragmentToDetailsFragment(articleList[position].uuid,
                articleList[position].source?.name.toString(),
                articleList[position].publishedAt.toString(),
                articleList[position].title.toString(),
                articleList[position].urlToImage.toString()
            )
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.findViewById<ImageView>(R.id.image).downloadFromUrl(articleList[position].urlToImage,
            placeHolderProgressBar(holder.view.context)
        )
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun updateArticleList(newArticleList: List<Article>){
        articleList.clear()
        articleList.addAll(newArticleList)
        notifyDataSetChanged()
    }
}