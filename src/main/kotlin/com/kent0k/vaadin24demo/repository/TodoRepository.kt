package com.kent0k.vaadin24demo.repository

import com.kent0k.vaadin24demo.dto.TodoDto

interface TodoRepository : CommonRepository<TodoDto> {

    fun markTodoReadyValue(objId: Int, isReady: Boolean): Boolean

}
