package az.sharif.bippyteam.service.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import az.sharif.bippyteam.model.User
import az.sharif.bippyteam.service.dao.UserDao

@Database(entities = [User::class], version=2)
abstract class UserDatabase:RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        private var UserDataBaseINSTANCE : UserDatabase?=null

        fun getInstance(context:Context):UserDatabase{
            if(UserDataBaseINSTANCE==null){
                UserDataBaseINSTANCE = Room.databaseBuilder(context, UserDatabase::class.java,"user_database").build()
            }
            return UserDataBaseINSTANCE!!
        }
    }

}