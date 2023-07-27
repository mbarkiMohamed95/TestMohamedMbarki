package com.example.data.local.typeCoverter

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.data.local.entitys.LocalUserLocationModel
import com.example.data.local.entitys.LocalUserModel
import com.example.data.local.entitys.LocalUserPictureModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


@TypeConverters
class UserTypeConverter {
    @TypeConverter
    fun stringToUserLocal(json: String?): LocalUserModel? {
        val gson = Gson()
        val type: Type? = object : TypeToken<LocalUserModel?>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun userLocalToString(list: LocalUserModel?): String? {
        val gson = Gson()
        val type: Type? = object : TypeToken<LocalUserModel?>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun stringToListUserLocal(json: String?): List<LocalUserModel?>? {
        val gson = Gson()
        val type: Type? = object : TypeToken<List<LocalUserModel?>?>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun listUserLocalToString(list: List<LocalUserModel?>?): String? {
        val gson = Gson()
        val type: Type? = object : TypeToken<List<LocalUserModel?>?>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun stringToLocalUserLocationModel(json: String?): LocalUserLocationModel? {
        val gson = Gson()
        val type: Type? = object : TypeToken<LocalUserLocationModel?>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun localUserLocationModelToString(list: LocalUserLocationModel?): String? {
        val gson = Gson()
        val type: Type? = object : TypeToken<LocalUserLocationModel?>() {}.type
        return gson.toJson(list, type)
    }


    @TypeConverter
    fun stringToLocalUserPictureModel(json: String?): LocalUserPictureModel? {
        val gson = Gson()
        val type: Type? = object : TypeToken<LocalUserPictureModel?>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun localUserPictureModelToString(list: LocalUserPictureModel?): String? {
        val gson = Gson()
        val type: Type? = object : TypeToken<LocalUserPictureModel?>() {}.type
        return gson.toJson(list, type)
    }


}