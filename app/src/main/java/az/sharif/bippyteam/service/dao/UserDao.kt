package az.sharif.bippyteam.service.dao

import androidx.room.Insert

interface UserDao {
    @Insert
    suspend fun addUser()
}