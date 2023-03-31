package az.sharif.bippyteam.model

import androidx.room.PrimaryKey


data class Article(

    val source: NewsSource?,
    val author: String?=null,
    val title: String?=null,
    val description:String?=null,
    val url: String?=null,
    val urlToImage:String?=null,
    val publishedAt:String?=null,
    val content: String?=null
){
    //@PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
