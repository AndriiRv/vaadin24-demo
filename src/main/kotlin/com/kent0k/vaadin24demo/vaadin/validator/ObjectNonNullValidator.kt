package com.kent0k.vaadin24demo.vaadin.validator

import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.binder.ValueContext
import com.vaadin.flow.data.validator.AbstractValidator

class ObjectNonNullValidator<T>(errorMessage: String?) : AbstractValidator<T>(errorMessage) {

    override fun apply(value: T, context: ValueContext?): ValidationResult {
        return toResult(value, value != null)
    }
}
