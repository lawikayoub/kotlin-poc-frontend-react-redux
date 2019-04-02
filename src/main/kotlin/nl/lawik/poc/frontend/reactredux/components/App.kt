package nl.lawik.poc.frontend.reactredux.components

import nl.lawik.poc.frontend.reactredux.containers.addTodo
import nl.lawik.poc.frontend.reactredux.containers.visibleTodoList
import react.RBuilder
import react.dom.div

fun RBuilder.app() =
    div {
        addTodo {}
        visibleTodoList {}
        footer()
    }