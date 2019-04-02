package nl.lawik.poc.frontend.reactredux.actions

import nl.lawik.poc.frontend.reactredux.enums.VisibilityFilter
import redux.RAction

private var nextTodoId = 1

class SetVisibilityFilter(val filter: VisibilityFilter) : RAction

class AddTodo(val text: String): RAction {
    val id = nextTodoId++
}

class ToggleTodo(val id: Int): RAction

