package com.kent0k.vaadin24demo.service

import com.kent0k.vaadin24demo.dto.TodoDto
import com.kent0k.vaadin24demo.repository.TodoRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TodoServiceImpl(private val todoRepository: TodoRepository) : TodoService {

    override fun save(obj: TodoDto): Boolean {
        val lastId = getLastId()
        obj.id = if (lastId != null) lastId + 1 else 1
        obj.createdDateTime = LocalDateTime.now()

        return todoRepository.save(obj)
    }

    override fun findById(objId: Int): TodoDto? {
        return todoRepository.findById(objId)
    }

    override fun findAll(): List<TodoDto> {
        return todoRepository.findAll()
    }

    override fun update(obj: TodoDto): Boolean {
        obj.updatedDateTime = LocalDateTime.now()

        return todoRepository.update(obj)
    }

    override fun markTodoReadyValue(objId: Int, isReady: Boolean): Boolean {
        return todoRepository.markTodoReadyValue(objId, isReady)
    }

    override fun delete(objId: Int): Boolean {
        return todoRepository.delete(objId)
    }

    override fun getLastId(): Int? {
        return todoRepository.getLastId()
    }
}
