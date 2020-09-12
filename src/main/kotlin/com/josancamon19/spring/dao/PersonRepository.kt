package com.josancamon19.spring.dao

import com.josancamon19.spring.model.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person, Long>