package com.example.helpme

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository <Posts, Int>