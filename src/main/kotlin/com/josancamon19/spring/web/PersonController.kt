package com.josancamon19.spring.web

import com.josancamon19.spring.business.IPersonBusiness
import com.josancamon19.spring.exception.BusinessException
import com.josancamon19.spring.exception.NotFoundException
import com.josancamon19.spring.model.Person
import com.josancamon19.spring.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_PERSONAS)
class PersonController {

    @Autowired
    val personBusiness: IPersonBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Person>> {
        return try {
            ResponseEntity(personBusiness!!.list(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun listById(@PathVariable id: Long): ResponseEntity<Person> {
        return try {
            ResponseEntity(personBusiness!!.load(id), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("")
    fun create(@RequestBody person: Person): ResponseEntity<Person> {
        return try {
            ResponseEntity(personBusiness!!.save(person), HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody person: Person): ResponseEntity<Any> {
        return try {
            personBusiness!!.save(person)
            ResponseEntity(HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            personBusiness!!.remove(id)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}