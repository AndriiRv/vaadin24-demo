package com.kent0k.vaadin24demo.vaadin

import com.kent0k.vaadin24demo.dto.UserDto
import com.kent0k.vaadin24demo.service.UserService
import com.kent0k.vaadin24demo.vaadin.validator.ObjectNonNullValidator
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.datepicker.DatePicker
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.Binder
import com.vaadin.flow.data.validator.DateRangeValidator
import com.vaadin.flow.data.validator.StringLengthValidator
import com.vaadin.flow.router.HasDynamicTitle
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Route(value = "create-user", layout = MainLayout::class)
class CreateUserLayout(private val userService: UserService) : VerticalLayout(), HasDynamicTitle {

    init {
        init()
    }

    private fun init() {
        val binder: Binder<UserDto> = Binder(UserDto::class.java)

        val firstNameField = TextField("First name", "John")
        binder.forField(firstNameField)
            .withValidator(StringLengthValidator("Minimum of 2 and maximum of 20 characters allowed", 2, 20))
            .bind("firstName")

        val lastNameField = TextField("Last name", "Smith")
        binder.forField(lastNameField)
            .withValidator(StringLengthValidator("Minimum of 2 and maximum of 20 characters allowed", 2, 20))
            .bind("lastName")

        val birthDateField = DatePicker("Birth date")
        birthDateField.placeholder = "12/31/1988"
        binder.forField(birthDateField)
            .withValidator(ObjectNonNullValidator("Date cannot be empty"))
            .withValidator(
                DateRangeValidator(
                    "Your birth date should within a range of 1900 to 2100",
                    LocalDate.of(1900, 1, 1),
                    LocalDate.of(2100, 1, 1)
                )
            )
            .bind("birthDate")

        val saveButton = Button("Save user")
        saveButton.addClickListener {
            val user = UserDto()

            binder.writeBean(user)
            userService.save(user)
            Notification.show("User '${user.firstName} ${user.lastName}' has saved")
            binder.refreshFields()
        }

        add(firstNameField, lastNameField, birthDateField, saveButton)
    }

    override fun getPageTitle(): String {
        return "Create new user"
    }
}
