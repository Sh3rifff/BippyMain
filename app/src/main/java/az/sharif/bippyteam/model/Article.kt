package az.sharif.bippyteam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
    @ColumnInfo(name="source")
    val source: NewsSource,
    @ColumnInfo(name="author")
    val author: String?=null,
    @ColumnInfo(name="title")
    val title: String?=null,
    @ColumnInfo(name="description")
    val description:String?=null,
    @ColumnInfo(name="url")
    val url: String?=null,
    @ColumnInfo(name="urlToImage")
    val urlToImage:String?=null,
    @ColumnInfo(name="publishedAt")
    val publishedAt:String?=null,
    @ColumnInfo(name="content")
    val content: String?=null
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
