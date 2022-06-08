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
                Users(666666666, "Sergio", "Gonzalez", "Montero", 20),
                Users(111111111, "Paco", "Garcia", "Valero", 40),
                Users(222222222, "Alicia", "Montero", "Jimenez", 50),
                Users(333333333, "Gema", "Matellanes", "Martinez", 19),
                Users(445645667, "Eustaquea", "Lorenza", "Fernandez", 12),
                Users(567889536, "Ramira", "Garcia", "Valero", 15),
                Users(444444444, "David", "Ruiz", "Prada", 63),
                Users(555555555, "Alejandro", "Fernandez", "Porras", 56),
                Users(777777777, "Angel", "Lozano", "Paños", 37),
                Users(634746758, "Antonio", "Martinez", "Garcia", 52),
                Users(656787912, "Samuel", "Porcuna", "Garcia", 82),
                Users(778756436, "Alfonso", "Garcia", "Ramirez", 32)
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