package com.kent0k.vaadin24demo.service

import com.kent0k.vaadin24demo.dto.UserDto

interface UserService : CommonService<UserDto> {

    fun markUserActiveValue(objId: Int, isActive: Boolean): Boolean

}
