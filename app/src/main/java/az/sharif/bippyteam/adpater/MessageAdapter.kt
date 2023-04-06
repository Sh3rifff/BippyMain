package az.sharif.bippyteam.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import az.sharif.bippyteam.R
import az.sharif.bippyteam.model.Message
import az.sharif.bippyteam.util.downloadFromUrl
import az.sharif.bippyteam.util.placeHolderProgressBar
import az.sharif.bippyteam.view.fragment.NewsFragmentDirections
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MessageAdapter(var messageList: List<Message>): RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    class MessageViewHolder(var view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.item_container_user,parent,false)
        return MessageViewHolder(view)
    }


    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {

        holder.view.findViewById<TextView>(R.id.textName).text=messageList[position].name
        holder.view.findViewById<TextView>(R.id.textEmail).text=messageList[position].lastMessage
        holder.view.findViewById<ImageView>(R.id.imageProfile).downloadFromUrl(messageList[position].profilImage,
            placeHolderProgressBar(holder.view.context)
        )

        holder.view.setOnClickListener{
            /*val action= MessageFragmentDirections.actionNewsFragmentToDetailsFragment()
            Navigation.findNavController(it).navigate(action)*/
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    /*fun updateArticleList(newArticleList: List<Article>){
        articleList.clear()
        articleList.addAll(newArticleList)
        notifyDataSetChanged()
    }
    fun setFilteredList(mList:ArrayList<Article>){
        //this.articleList=mList
        articleList.clear()
        articleList.addAll(mList)
        notifyDataSetChanged()
    }*/
}