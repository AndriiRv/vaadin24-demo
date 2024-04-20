package com.kent0k.vaadin24demo.service

interface CommonService<T> {

    fun save(obj: T): Boolean

    fun findById(objId: Int): T?

    fun findAll(): List<T>

    fun update(obj: T): Boolean

    fun delete(objId: Int): Boolean

    fun getLastId(): Int?
}
