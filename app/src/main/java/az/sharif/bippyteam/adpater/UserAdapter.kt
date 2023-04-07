package az.sharif.bippyteam.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import az.sharif.bippyteam.R
import az.sharif.bippyteam.model.User
import az.sharif.bippyteam.util.downloadFromUrl
import az.sharif.bippyteam.util.placeHolderProgressBar
import az.sharif.bippyteam.view.fragment.MessageFragmentDirections
import az.sharif.bippyteam.view.fragment.UsersFragmentDirections

class UserAdapter(var userList: List<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(var view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.item_container_user,parent,false)
        return UserViewHolder(view)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.view.findViewById<TextView>(R.id.textName).text=userList[position].name
        holder.view.findViewById<TextView>(R.id.textEmail).text=userList[position].email
        holder.view.findViewById<ImageView>(R.id.imageProfile).downloadFromUrl(userList[position].profilImage,
            placeHolderProgressBar(holder.view.context)
        )

        holder.view.setOnClickListener{
            val action = UsersFragmentDirections.actionUsersFragmentToChatFragment(userList[position].name,userList[position].profilImage)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return userList.size
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