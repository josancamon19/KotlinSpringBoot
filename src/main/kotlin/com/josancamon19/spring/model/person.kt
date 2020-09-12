package com.josancamon19.spring.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "persons_table")
data class Person(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
        val name: String,
        val lastName: String,
        val dateBorn: Date? = null)