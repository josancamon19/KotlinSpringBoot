package com.josancamon19.spring.business

import com.josancamon19.spring.dao.PersonRepository
import com.josancamon19.spring.exception.BusinessException
import com.josancamon19.spring.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonBusiness : IPersonBusiness {
    @Autowired
    val repository: PersonRepository? = null

    @Throws(BusinessException::class)
    override fun list(): List<Person> {
        try {
            return repository!!.findAll()
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    override fun load(personId: Long): Person {
        val op: Optional<Person>
        try {
            op = repository!!.findById(personId)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
        if (!op.isPresent) {
            throw BusinessException("Person with id $personId not found")
        }
        return op.get()
    }

    override fun save(person: Person): Person {
        try {
            return repository!!.save(person)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    override fun remove(personId: Long) {
        val op: Optional<Person>
        try {
            op = repository!!.findById(personId)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
        if (!op.isPresent) {
            throw BusinessException("Person with id $personId not found")
        } else {
            try {
                repository!!.deleteById(personId)
            } catch (e: Exception) {
                throw BusinessException(e.message)
            }
        }
    }

}