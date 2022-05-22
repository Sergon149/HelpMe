package com.example.helpme

import com.google.gson.Gson

class UserList {
    val listUsers = mutableListOf<Users>()
    override fun toString(): String{
        val gson = Gson()
        return gson.toJson(this)
    }
}