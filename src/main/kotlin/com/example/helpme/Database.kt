package com.example.helpme

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.security.MessageDigest
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

@Configuration
class Database {
    @Bean
    fun initDatabase(userRepository: UserRepository, postRepository: PostRepository, adminUsersRepository: AdminUsersRepository): CommandLineRunner{
        return CommandLineRunner{
            println("Database being created")
            val listUsers = listOf(
                Users(666666666, "Sergio", "Gonzalez", "Montero", 20)
            )
            val listAdmins = listOf(
                AdminUsers(2222,"primeradmin")
            )
            val listPost = listOf(
                Posts("Cita Médico", "Cita el dia 22 a las 11 de la mañana", 666666666),
                Posts("Vacuna Covid-19", "Cita el dia 2 a las 9 de la mañana en el Gregorio Marañon", 666666666)
            )
            listUsers.forEach { userRepository.save(it) }
            listAdmins.forEach { adminUsersRepository.save(it) }
            listPost.forEach { postRepository.save(it) }
            println("Database created")
        }
    }
}