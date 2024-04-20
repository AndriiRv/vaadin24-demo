package com.kent0k.vaadin24demo.repository

import com.kent0k.vaadin24demo.dto.UserDto

interface UserRepository : CommonRepository<UserDto> {

    fun markUserActiveValue(objId: Int, isActive: Boolean): Boolean

}
