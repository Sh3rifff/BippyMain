package az.sharif.bippyteam.model

import androidx.room.Entity


@Entity ("users")
data class User(
    val name:String,
    val password:String
)
