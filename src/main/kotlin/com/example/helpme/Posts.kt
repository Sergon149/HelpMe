package com.example.helpme

import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Posts (var emailSubject : String, var messageBody : String, var idUser : Int) {

    @GeneratedValue
    @Id
    var id = 0

    override fun toString(): String{
        val gson = Gson()
        return gson.toJson(this)
    }
}
