package com.example.helpme

import com.google.gson.Gson

class PostList{
    val listwithpost = mutableListOf<Posts>()
    override fun toString(): String{
        val gson = Gson()
        return gson.toJson(this)
    }
}