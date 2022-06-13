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
                Users(655787697, "Sergio", "Gonzalez", "Montero", 20),
                Users(111111111, "Paco", "Garcia", "Valero", 41),
                Users(222222222, "Alicia", "Montero", "Jimenez", 51),
                Users(333333333, "Gema", "Matellanes", "Martinez", 19),
                Users(445645667, "Eustaquia", "Lorenza", "Fernandez", 21),
                Users(567889536, "Ramira", "Garcia", "Valero", 15),
                Users(444444444, "David", "Ruiz", "Prada", 63),
                Users(555555555, "Alejandro", "Fernandez", "Porras", 56),
                Users(777777777, "Angel", "Lozano", "Paños", 37),
                Users(634746758, "Antonio", "Martinez", "Garcia", 52),
                Users(656787912, "Samuel", "Porcuna", "Garcia", 82),
                Users(778756436, "Alfonso", "Garcia", "Ramirez", 32),
                Users(124567753, "Maria", "Valle", "Marcos", 85),
                Users(536472536, "Eduardo", "Jose", "Vicente", 90),
                Users(879625783, "Maria", "Elisa", "Silva", 71),
                Users(383563456, "Anastasio", "Cornejo", "Mengual", 31),
                Users(545642124, "Alexia", "Valenzuela", "Lara", 73),
                Users(442472306, "Milena", "Antia", "Bustos", 87),
                Users(548756870, "Felipe", "Ferrando", "Campillo", 46),
                Users(423489633, "Marta", "Mengual", "Castillo", 42),
                Users(141546789, "Rebeca", "Terán", "Martinez", 56),
                Users(734539877, "Lucas", "Jimenez", "Benitez", 68),
                Users(424649843, "Carla", "Alvarez", "Pineda", 54),
                Users(886713896, "Natalia", "Gomez", "Rivas", 24),
                Users(254348769, "David", "De Miguel", "Mejia", 33),
                Users(345486748, "Adrian", "Verdugo", "Collado", 51),
                Users(246879467, "Luz", "Caro", "Rodriguez", 18),
                Users(897547567, "Carlos", "Sanchez", "Clemente", 96),
                Users(175676897, "Alvaro", "Ruiz", "Fernandez", 35)
            )
            val listAdmins = listOf(
                AdminUsers(2222,"primeradmin"),
                AdminUsers(1602,"segundoadmin"),
                AdminUsers(6131,"terceradmin")
            )
            val listPost = listOf(
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 655787697),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 111111111),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 222222222),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 333333333),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 445645667),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 567889536),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 444444444),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 555555555),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 777777777),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 634746758),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 656787912),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 778756436),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 124567753),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 536472536),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 879625783),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 778756436),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 545642124),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 442472306),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 548756870),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 423489633),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 141546789),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 734539877),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 424649843),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 886713896),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 254348769),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 345486748),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 246879467),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 897547567),
                Posts("¡Bienvenido a HELPME!", "Nos alegra que usted confie en nosotros", 175676897),
                Posts("Cita Médico", "Cita el dia 22 a las 11 de la mañana", 655787697),
                Posts("Vacuna Covid-19", "Cita el dia 2 a las 9 de la mañana en el Gregorio Marañon", 655787697)
            )
            listUsers.forEach { userRepository.save(it) }
            listAdmins.forEach { adminUsersRepository.save(it) }
            listPost.forEach { postRepository.save(it) }
            println("Database created")
        }
    }
}