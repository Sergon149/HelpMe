package com.example.helpme

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController (private val userRepository: UserRepository, private val postRepository: PostRepository, private val adminUsersRepository: AdminUsersRepository) {

    val type = "AES/ECB/PKCS5Padding"

    @GetMapping("showAll")
    fun showAll(){
        println("Usuarios ->")
        userRepository.findAll().forEach { println(it) }
        println("Mensajes ->")
        postRepository.findAll().forEach { println(it) }
        println("Admins ->")
        adminUsersRepository.findAll().forEach { println(it) }
    }
    //http://localhost:8082/addUser/666666666/Sergio/Gonzalez/Montero/20
    //http://localhost:8082/addUser/555555555/Alicia/Montero/Jimenez/51
    //http://localhost:8082/addUser/444444444/Gema/Matellanes/Martinez/19
    //http://localhost:8082/addUser/333333333/Alejandro/Fernandez/Porras/22
    @GetMapping("addUser/{number}/{name}/{lastname1}/{lastname2}/{age}")
    fun addUser(@PathVariable number: Int, @PathVariable name : String, @PathVariable lastname1 : String, @PathVariable lastname2 : String, @PathVariable age : Int){
        val newUser = Users(number, name, lastname1, lastname2, age)
        userRepository.save(newUser)
    }

    @GetMapping("delete/{idUser}")
    fun deleteUser(@PathVariable idUser: Int){
        userRepository.deleteById(idUser)
        postRepository.findAll().forEach {
            if (it.idUser == idUser)
                postRepository.deleteById(it.id)
        }
        showAll()
    }

    //http://localhost:8082/addPost/Cita Médico/Cita el dia 22 a las 11 de la mañana/666666666
    //http://localhost:8082/addPost/Rehabilitación/Cita el dia 12 a las 10 de la mañana/666666666
    @GetMapping("addPost/{subject}/{body}/{number}")
    fun addPost(@PathVariable subject : String, @PathVariable body : String, @PathVariable number : Int){
        val user = userRepository.getById(number)
        val newPost = Posts(subject, body, user.telephoneNumber)
        postRepository.save(newPost)
    }

    //http://localhost:8082/GetList/666666666
    @GetMapping("GetList/{idU}")
    fun getList(@PathVariable idU : Int): Any {
        val list = PostList()
        postRepository.findAll().forEach {
            if (it.idUser == idU){ list.listwithpost.add(it) }
        }
        return if (list.listwithpost.size>0)
            list
        else
            "Empty"
    }
    //http://localhost:8082/CheckAdmin/2222/primeradmin
    @GetMapping("CheckAdmin/{id}/{password}")
    fun checkAdmin(@PathVariable id : Int, @PathVariable password : String): Any {
        val admin = AdminUsers(id, password)
        println(admin)
        var respuesta = ""
        var adminId=0
        var exists: Boolean
        if (checkId(admin)){
            println("Admin existe")
            if (checkPass(admin)){
                exists = true
                adminId = admin.Idemployee
            }else{
                exists = false
                respuesta="Incorrecta"
            }
        }else {
            exists = false
            respuesta = "No existe"
        }

        return if (exists)
            adminId
        else
            respuesta
    }

    //http://localhost:8082/allUsers
    @GetMapping("allUsers")
    fun allUsers(): Any {
        val list = UserList()
        userRepository.findAll().forEach { list.listUsers.add(it) }
        return if (list.listUsers.size>0)
            list
        else
            "Empty"
    }

    //http://localhost:8082/getUser/666666666
    @GetMapping("getUser/{id}")
    fun getUser(@PathVariable id: Int): Users {
        return userRepository.getById(id)
    }

    @GetMapping("getName/{id}")
    fun getName(@PathVariable id: Int) : String {
        val user = userRepository.getById(id)
        return "${user.name} ${user.lastname1} ${user.lastname2}"
    }

    //http://localhost:8082/getless20
    @GetMapping("getless20")
    fun getless20(): String {
        var cont = 0
        userRepository.findAll().forEach {
            if(it.age < 20)
                cont += 1
        }
        println(cont)
        return cont.toString()
    }

    //http://localhost:8082/get20_29
    @GetMapping("get20_29")
    fun get20_29(): String {
        var cont = 0
        userRepository.findAll().forEach {
            if(it.age in 20..29)
                cont += 1
        }
        println(cont)
        return cont.toString()
    }

    //http://localhost:8082/get30_39
    @GetMapping("get30_39")
    fun get30_39(): String {
        var cont = 0
        userRepository.findAll().forEach {
            if(it.age in 30..39)
                cont += 1
        }
        println(cont)
        return cont.toString()
    }

    //http://localhost:8082/get40_49
    @GetMapping("get40_49")
    fun get40_49(): String {
        var cont = 0
        userRepository.findAll().forEach {
            if(it.age in 40..49)
                cont += 1
        }
        println(cont)
        return cont.toString()
    }

    //http://localhost:8082/get50_59
    @GetMapping("get50_59")
    fun get50_59(): String {
        var cont = 0
        userRepository.findAll().forEach {
            if(it.age in 50..59)
                cont += 1
        }
        println(cont)
        return cont.toString()
    }

    //http://localhost:8082/get60_69
    @GetMapping("get60_69")
    fun get60_69(): String {
        var cont = 0
        userRepository.findAll().forEach {
            if(it.age in 60..69)
                cont += 1
        }
        println(cont)
        return cont.toString()
    }

    //http://localhost:8082/get70_79
    @GetMapping("get70_79")
    fun get70_79(): String {
        var cont = 0
        userRepository.findAll().forEach {
            if(it.age in 70..79)
                cont += 1
        }
        println(cont)
        return cont.toString()
    }

    //http://localhost:8082/getmore80
    @GetMapping("getmore80")
    fun getmore80(): String {
        var cont = 0
        userRepository.findAll().forEach {
            if(it.age >= 80)
                cont += 1
        }
        println(cont)
        return cont.toString()
    }

    @GetMapping("checkUser/{number}")
    fun checkUser(@PathVariable number: Int): Boolean {
        var exist=false
        userRepository.findAll().forEach {
            if (it.telephoneNumber == number ){
                exist=true
            }
        }
        return exist
    }

    fun checkId(admin : AdminUsers): Boolean{
        var exist=false
        adminUsersRepository.findAll().forEach {
            if (it.Idemployee == admin.Idemployee)
                exist=true
        }
        return exist
    }

    fun checkPass(admin : AdminUsers): Boolean{
        var exist=false
        adminUsersRepository.findAll().forEach {
            if (it.password == admin.password)
                exist=true
        }
        return exist
    }
}

