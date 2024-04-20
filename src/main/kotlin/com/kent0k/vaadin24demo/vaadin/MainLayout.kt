package com.kent0k.vaadin24demo.vaadin

import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.applayout.DrawerToggle
import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.RouterLink

class MainLayout : AppLayout() {

    init {
        addToNavbar(DrawerToggle())
        addToNavbar(H2("TODO application"))

        val menuBar = VerticalLayout()
        menuBar.add(RouterLink("Home", HomeLayout::class.java))
        menuBar.add(RouterLink("Create user", CreateUserLayout::class.java))
        menuBar.add(RouterLink("Create todo", CreateTodoLayout::class.java))
        menuBar.add(RouterLink("Show users", UsersLayout::class.java))
        menuBar.add(RouterLink("Show todos", TodosLayout::class.java))
        addToDrawer(menuBar)
    }
}
