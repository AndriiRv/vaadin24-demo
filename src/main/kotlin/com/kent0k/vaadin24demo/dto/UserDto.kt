package com.kent0k.vaadin24demo.dto

import java.time.LocalDate

data class UserDto(
    var id: Int = 0,
    var firstName: String = "",
    var lastName: String = "",
    var birthDate: LocalDate? = null,
    var isActive: Boolean = false,
    var todos: List<TodoDto>? = emptyList()
)
