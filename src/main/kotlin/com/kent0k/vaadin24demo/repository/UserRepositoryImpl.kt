package com.kent0k.vaadin24demo.repository

import com.kent0k.vaadin24demo.dto.UserDto
import com.kent0k.vaadin24demo.exception.ElementNotFound
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {

    private val users: MutableList<UserDto> = mutableListOf()

    override fun save(obj: UserDto): Boolean {
        return this.users.add(obj)
    }

    override fun findById(objId: Int): UserDto? {
        return this.users.firstOrNull { it.id == objId }
    }

    override fun findAll(): List<UserDto> {
        return this.users.toList()
    }

    override fun update(obj: UserDto): Boolean {
        val user = this.findById(obj.id) ?: throw ElementNotFound("User not found. Please try again.")

        user.firstName = obj.firstName
        user.lastName = obj.lastName
        user.birthDate = obj.birthDate
        user.isActive = obj.isActive

        return true
    }

    override fun markUserActiveValue(objId: Int, isActive: Boolean): Boolean {
        val user = this.findById(objId) ?: throw ElementNotFound("User not found. Please try again.")
        user.isActive = isActive

        return true
    }

    override fun delete(objId: Int): Boolean {
        val user: UserDto = findById(objId) ?: throw ElementNotFound("User not found. Please try again.")
        this.users.remove(user)

        return true
    }

    override fun getLastId(): Int? {
        return this.users.maxOfOrNull { it.id }
    }
}
