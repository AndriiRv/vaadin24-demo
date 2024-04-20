package com.kent0k.vaadin24demo.repository

import com.kent0k.vaadin24demo.dto.TodoDto
import com.kent0k.vaadin24demo.exception.ElementNotFound
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class TodoRepositoryImpl : TodoRepository {

    private val todos: MutableList<TodoDto> = mutableListOf()

    override fun save(obj: TodoDto): Boolean {
        return this.todos.add(obj)
    }

    override fun findById(objId: Int): TodoDto? {
        return this.todos.firstOrNull { it.id == objId }
    }

    override fun findAll(): List<TodoDto> {
        return this.todos.toList()
    }

    override fun update(obj: TodoDto): Boolean {
        val todo = this.findById(obj.id) ?: throw ElementNotFound("Todo not found. Please try again.")

        todo.userId = obj.userId
        todo.title = obj.title
        todo.description = obj.description

        return true
    }

    override fun markTodoReadyValue(objId: Int, isReady: Boolean): Boolean {
        val todo = this.findById(objId) ?: throw ElementNotFound("Todo not found. Please try again.")

        todo.isReady = isReady

        val updatedAndFinishedDateTime: LocalDateTime = LocalDateTime.now()
        todo.updatedDateTime = updatedAndFinishedDateTime
        todo.finishedDateTime = updatedAndFinishedDateTime

        return true
    }

    override fun delete(objId: Int): Boolean {
        val todo: TodoDto = findById(objId) ?: throw ElementNotFound("Todo not found. Please try again.")
        this.todos.remove(todo)

        return true
    }

    override fun getLastId(): Int? {
        return this.todos.maxOfOrNull { it.id }
    }
}
