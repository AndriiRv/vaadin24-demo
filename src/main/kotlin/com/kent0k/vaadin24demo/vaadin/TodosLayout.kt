package com.kent0k.vaadin24demo.vaadin

import com.kent0k.vaadin24demo.dto.TodoDto
import com.kent0k.vaadin24demo.service.TodoService
import com.vaadin.flow.component.checkbox.Checkbox
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer
import com.vaadin.flow.router.HasDynamicTitle
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Route(value = "todos", layout = MainLayout::class)
class TodosLayout(private val todoService: TodoService) : VerticalLayout(), HasDynamicTitle {

    init {
        init()
    }

    private fun init() {
        val allTodos: List<TodoDto> = todoService.findAll()
        if (allTodos.isEmpty()) {
            val div = Div()
            div.text = "No one todos was created."
            add(div)
            return
        }

        val grid = Grid<TodoDto>()
        add(grid)

        grid.setItems(todoService.findAll())

        grid.addColumn(TodoDto::id).setHeader("ID").setWidth("2px")
        grid.addColumn(TodoDto::title).setHeader("Title")
        grid.addColumn(TodoDto::description).setHeader("Description")

        grid.addComponentColumn {
            val checkbox = Checkbox()
            checkbox.value = it.isReady
            checkbox.addClickListener { e ->
                todoService.markTodoReadyValue(it.id, checkbox.value)
            }
            return@addComponentColumn checkbox
        }.setHeader("Is ready?").setWidth("5px")

        grid.addColumn(LocalDateTimeRenderer(TodoDto::createdDateTime)).setHeader("Created datetime")
        grid.addColumn(LocalDateTimeRenderer(TodoDto::updatedDateTime)).setHeader("Updated datetime")
        grid.addColumn(LocalDateTimeRenderer(TodoDto::finishedDateTime)).setHeader("Finished datetime")
    }

    override fun getPageTitle(): String {
        return "All TODOs"
    }
}
