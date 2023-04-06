package az.sharif.bippyteam.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity ("users")
data class MyUsers(
    @PrimaryKey(autoGenerate = true) val id:Long,
    val name:String,
    val password:String
)
