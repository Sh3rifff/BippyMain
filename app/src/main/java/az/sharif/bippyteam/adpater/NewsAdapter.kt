package az.sharif.bippyteam.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import az.sharif.bippyteam.R
import az.sharif.bippyteam.model.Article

class NewsAdapter(val articleList: ArrayList<Article>): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(var view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.item_layout,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        holder.view.findViewById<TextView>(R.id.tvTitle).text=articleList[position].title
        holder.view.findViewById<TextView>(R.id.tvSource).text=articleList[position].source?.name



        /*holder.view.setOnClickListener{
            val action=FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.imageView.downloadFromUrl(countryList[position].imageUrl,
            placeHolderProgressBar(holder.view.context)
        )*/
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