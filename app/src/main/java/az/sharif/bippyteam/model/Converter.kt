package az.sharif.bippyteam.model

import androidx.room.TypeConverter

class Converter {
    val PATTERN = "BABA"
    @TypeConverter
    fun fromSource(source: NewsSource):String{
        return  source.id+PATTERN+source.name
    }
    @TypeConverter
    fun toSource(str:String):NewsSource{
        val list = str.split(PATTERN)
        return NewsSource(list[0],list[1])
    }
}