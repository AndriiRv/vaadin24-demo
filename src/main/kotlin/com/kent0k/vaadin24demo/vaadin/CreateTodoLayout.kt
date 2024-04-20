package com.kent0k.vaadin24demo.vaadin

import com.kent0k.vaadin24demo.dto.TodoDto
import com.kent0k.vaadin24demo.dto.UserDto
import com.kent0k.vaadin24demo.exception.ElementNotFound
import com.kent0k.vaadin24demo.service.TodoService
import com.kent0k.vaadin24demo.service.UserService
import com.kent0k.vaadin24demo.vaadin.validator.ObjectNonNullValidator
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.combobox.ComboBox
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.Binder
import com.vaadin.flow.data.validator.StringLengthValidator
import com.vaadin.flow.router.HasDynamicTitle
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Route(value = "create-todo", layout = MainLayout::class)
class CreateTodoLayout(private val userService: UserService, private val todoService: TodoService) : VerticalLayout(), HasDynamicTitle {

    init {
        init()
    }

    private fun init() {
        val binder: Binder<TodoDto> = Binder(TodoDto::class.java)

        val allUsers: List<UserDto> = userService.findAll()
        if (allUsers.isEmpty()) {
            val div = Div()
            div.text = "No one users was created."
            add(div)
            return
        }

        val userComboBox: ComboBox<Int> = ComboBox<Int>("User", allUsers.map { it.id }.toList())
        userComboBox.placeholder = "You need select any user"
        userComboBox.setItemLabelGenerator {
            val user = userService.findById(it) ?: throw ElementNotFound("User not found.")
            user.firstName + " " + user.lastName
        }
        binder.forField(userComboBox)
            .withValidator(ObjectNonNullValidator("You need select any user"))
            .bind("userId")

        val titleField = TextField("Title", "Wash the dishes")
        binder.forField(titleField)
            .withValidator(StringLengthValidator("Minimum of 5 and maximum of 50 characters allowed", 5, 50))
            .bind("title")

        val descriptionField = TextField("Description", "Please wash the dishes")
        binder.forField(descriptionField)
            .withValidator(StringLengthValidator("Minimum of 5 and maximum of 50 characters allowed", 5, 50))
            .bind("description")

        val saveButton = Button("Save TODO")
        saveButton.addClickListener {
            val todo = TodoDto()

            binder.writeBean(todo)
            todoService.save(todo)
            Notification.show("Todo '${todo.title}' has saved")
            binder.refreshFields()
        }

        add(userComboBox, titleField, descriptionField, saveButton)
    }

    override fun getPageTitle(): String {
        return "Create Todo"
    }
}
