package az.sharif.bippyteam.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import az.sharif.bippyteam.model.Article
import az.sharif.bippyteam.model.Converter
import az.sharif.bippyteam.service.dao.NewsDao


@Database(entities = arrayOf(Article::class),version = 1)
@TypeConverters(Converter::class)
abstract class NewsDatabase : RoomDatabase(){
    abstract fun newsDao(): NewsDao

    //Singleton

    companion object{
        @Volatile private var instance : NewsDatabase?=null

        private val lock = Any()

        operator fun invoke(context: Context)= instance?: synchronized(lock){
            instance?: makeDatabase(context).also{
                instance=it
            }
        }

        private fun makeDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,NewsDatabase::class.java,"newsDatabase"
        ).build()
    }
}
