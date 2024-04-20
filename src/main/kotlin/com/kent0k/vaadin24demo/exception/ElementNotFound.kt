package com.kent0k.vaadin24demo.exception

class ElementNotFound : RuntimeException {

    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}
