package com.kent0k.vaadin24demo.vaadin

import com.kent0k.vaadin24demo.dto.UserDto
import com.kent0k.vaadin24demo.service.TodoService
import com.kent0k.vaadin24demo.service.UserService
import com.vaadin.flow.component.checkbox.Checkbox
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.orderedlayout.FlexLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.data.renderer.LocalDateRenderer
import com.vaadin.flow.dom.Style
import com.vaadin.flow.router.HasDynamicTitle
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Route(value = "users", layout = MainLayout::class)
class UsersLayout(private val userService: UserService, private val todoService: TodoService) : VerticalLayout(), HasDynamicTitle {

    init {
        init()
    }

    private fun init() {
        val allUsers = userService.findAll()
        if (allUsers.isEmpty()) {
            val div = Div()
            div.text = "No one users was created."
            add(div)
            return
        }

        val grid = Grid<UserDto>()
        grid.style.setAlignItems(Style.AlignItems.FLEX_START)
        add(grid)

        for (user in userService.findAll()) {
            user.todos = todoService.findAll().filter { it.userId == user.id}.toList()
        }

        grid.setItems(userService.findAll())
        grid.asSingleSelect()

        grid.addColumn(UserDto::id).setHeader("ID").setWidth("2px")
        grid.addColumn(UserDto::firstName).setHeader("First name")
        grid.addColumn(UserDto::lastName).setHeader("LastName")
        grid.addColumn(LocalDateRenderer(UserDto::birthDate)).setHeader("Birth date")

        grid.addComponentColumn {
            val checkbox = Checkbox()
            checkbox.value = it.isActive
            checkbox.addClickListener { e ->
                userService.markUserActiveValue(it.id, checkbox.value)
            }
            return@addComponentColumn checkbox
        }.setHeader("Is active?").setWidth("5px")

        grid.addComponentColumn {
            val div = Div()

            if (it.todos!!.isEmpty()) {
                div.text = "Empty"
                add(div)
                return@addComponentColumn div
            }

            for (todo in it.todos!!) {
                val flexLayout = FlexLayout()
                val checkbox = Checkbox()
                flexLayout.add(checkbox)
                checkbox.value = todo.isReady
                checkbox.addClickListener { e ->
                    todoService.markTodoReadyValue(todo.id, checkbox.value)
                }
                flexLayout.add(Div(todo.id.toString() + ". " + todo.title))
                div.add(flexLayout)
            }
            return@addComponentColumn div
        }.setHeader("Todos")
    }

    override fun getPageTitle(): String {
        return "All users"
    }
}
