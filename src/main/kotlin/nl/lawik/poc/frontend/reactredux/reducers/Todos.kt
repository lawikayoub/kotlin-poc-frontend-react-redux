package nl.lawik.poc.frontend.reactredux.reducers

import nl.lawik.poc.frontend.reactredux.actions.AddTodo
import nl.lawik.poc.frontend.reactredux.actions.DeleteTodo
import nl.lawik.poc.frontend.reactredux.actions.EditTodo
import nl.lawik.poc.frontend.reactredux.actions.ToggleTodo
import nl.lawik.poc.frontend.reactredux.entities.Todo
import redux.RAction

fun todos(state: Array<Todo> = emptyArray(), action: RAction): Array<Todo> = when (action) {
    is AddTodo -> state + Todo(action.id, action.text, false)
    is ToggleTodo -> state.map {
        if (it.id == action.id) {
            it.copy(completed = !it.completed)
        } else {
            it
        }
    }.toTypedArray()
    is DeleteTodo -> state.filterNot { it.id == action.id }.toTypedArray()
    is EditTodo -> state.map {
        if(it.id == action.id) {
            it.copy(text = action.newText)
        } else {
            it
        }
    }.toTypedArray()
    else -> state
}