package com.josancamon19.spring.business

import com.josancamon19.spring.model.Person

interface IPersonBusiness {

    fun list():List<Person>
    fun load(personId: Long): Person
    fun save(person:Person): Person
    fun remove(personId: Long)

}