package com.kent0k.vaadin24demo.service

import com.kent0k.vaadin24demo.dto.UserDto
import com.kent0k.vaadin24demo.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun save(obj: UserDto): Boolean {
        val lastId = getLastId()
        obj.id = if (lastId != null) lastId + 1 else 1
        obj.isActive = true

        return userRepository.save(obj)
    }

    override fun findById(objId: Int): UserDto? {
        return userRepository.findById(objId)
    }

    override fun findAll(): List<UserDto> {
        return userRepository.findAll()
    }

    override fun update(obj: UserDto): Boolean {
        return userRepository.update(obj)
    }

    override fun markUserActiveValue(objId: Int, isActive: Boolean): Boolean {
        return userRepository.markUserActiveValue(objId, isActive)
    }

    override fun delete(objId: Int): Boolean {
        return userRepository.delete(objId)
    }

    override fun getLastId(): Int? {
        return userRepository.getLastId()
    }
}
