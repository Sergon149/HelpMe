package com.example.helpme

import org.springframework.web.bind.annotation.DeleteMapping
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

