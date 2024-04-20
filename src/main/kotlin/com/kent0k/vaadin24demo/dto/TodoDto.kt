package com.kent0k.vaadin24demo.dto

import java.time.LocalDateTime

data class TodoDto(
    var id: Int = 0,
    var userId: Int = 0,
    var title: String = "",
    var description: String = "",
    var isReady: Boolean = false,
    var createdDateTime: LocalDateTime? = null,
    var updatedDateTime: LocalDateTime? = null,
    var finishedDateTime: LocalDateTime? = null
)
