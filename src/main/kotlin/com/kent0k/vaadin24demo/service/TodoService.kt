package com.kent0k.vaadin24demo.service

import com.kent0k.vaadin24demo.dto.TodoDto

interface TodoService : CommonService<TodoDto> {

    fun markTodoReadyValue(objId: Int, isReady: Boolean): Boolean

}
