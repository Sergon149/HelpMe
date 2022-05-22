package com.example.helpme

import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class AdminUsers (@Id var Idemployee : Int, var password : String) {

    override fun toString(): String{
        val gson = Gson()
        return gson.toJson(this)
    }
}