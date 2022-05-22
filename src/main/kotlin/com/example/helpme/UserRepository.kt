package com.example.helpme

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Users, Int>