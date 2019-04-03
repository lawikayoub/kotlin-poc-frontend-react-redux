package nl.lawik.poc.frontend.reactredux.actions

import nl.lawik.poc.frontend.reactredux.enums.VisibilityFilter
import redux.RAction

class SetVisibilityFilter(val filter: VisibilityFilter) : RAction

class AddTodo(val text: String): RAction {
    private companion object {
      var nextTodoId = 1
    }
    val id = nextTodoId++
}

class ToggleTodo(val id: Int): RAction

