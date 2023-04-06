package az.sharif.bippyteam.service.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import az.sharif.bippyteam.model.MyUsers
import retrofit2.http.GET

@Dao
interface UserDao {
    @Query("Select * from users where name = :email and password = :pass")
    suspend fun validateUser(email:String,pass:String):MyUsers?
    @Insert
    suspend fun saveUser(myUsers: MyUsers)

    @Query("Select * from users")
    suspend fun getAllUsers():List<MyUsers>

    @Query("Delete from users")
    fun clearAll()

    @Query("Select * from users")
    suspend fun getOneUser():List<MyUsers>?


}