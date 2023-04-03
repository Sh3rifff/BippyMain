package az.sharif.bippyteam.model

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun fromSource(source: NewsSource):String{
        return  source.id+":"+source.name
    }
    @TypeConverter
    fun toSource(str:String):NewsSource{
        val list = str.split(":")
        return NewsSource(list[0],list[1])
    }
}