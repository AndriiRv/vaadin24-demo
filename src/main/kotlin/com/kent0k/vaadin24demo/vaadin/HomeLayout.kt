package com.kent0k.vaadin24demo.vaadin

import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.html.H3
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.HasDynamicTitle
import com.vaadin.flow.router.Route
import com.vaadin.flow.router.RouteAlias
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope

@Route(value = "home", layout = MainLayout::class)
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RouteAlias(value = "", layout = MainLayout::class)
class HomeLayout : VerticalLayout(), HasDynamicTitle {

    init {
        add(H1("Welcome to TODO application!"))
        add(H3("Here you can add some of TODO task to some of user."))
    }

    override fun getPageTitle(): String {
        return "TODO application"
    }
}
