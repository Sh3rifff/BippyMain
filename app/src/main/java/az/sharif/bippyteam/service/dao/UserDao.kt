package az.sharif.bippyteam.service.dao

import androidx.room.Dao
import androidx.room.Query
import az.sharif.bippyteam.model.User

@Dao
interface UserDao {
    @Query("Select * from users where name =: email and password =: pass")
    fun findUserByMail(email:String,pass:String):User?
}