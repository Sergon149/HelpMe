package com.example.helpme

import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Users (@Id var telephoneNumber: Int, var name: String, var lastname1 : String, var lastname2 : String, var age: Int){

    override fun toString(): String{
        val gson = Gson()
        return gson.toJson(this)
    }
}